package brawljars.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import brawljars.common.PaginationRequest;
import brawljars.common.PaginationResponse;
import brawljars.connector.Connector;
import brawljars.connector.ConnectorException;
import brawljars.connector.RequestContext;
import lombok.Getter;
import lombok.Setter;

@ExtendWith(MockitoExtension.class)
class BaseApiTest {

  private static final String API_KEY = "apiKey";
  private static final String PART = "part";
  private static final String URL = "url";

  private BaseApi unitUnderTest;

  @Mock
  private Connector connector;

  private ApiContext apiContext;

  private PaginationRequest request;

  @Getter
  @Setter
  class FooResponse extends PaginationResponse<FooRequest> {
  }

  @BeforeEach
  void setUp() {
    apiContext = new ApiContext(URL, API_KEY, connector);
    request = new FooRequest(100, "after", "before", true);
    unitUnderTest = new BaseApi(apiContext);
  }

  @Getter
  @Setter
  class FooRequest extends PaginationRequest {

    protected FooRequest(int limit, String after, String before, boolean storeRawResponse) {
      super(limit, after, before, storeRawResponse);
    }

    @Override
    public Map<String, String> getRestParameters() {
      Map<String, String> map = super.getRestParameters();
      map.put("param", "value");
      return map;
    }

  }

  @Test
  void get_whenWithNullPart_shouldThrowException() {

    assertThrows(IllegalArgumentException.class,
        () -> unitUnderTest.get(null, request, FooResponse.class));
  }

  @Test
  void get_whenWithEmptyPart_shouldThrowException() {

    assertThrows(IllegalArgumentException.class, () -> unitUnderTest.get(EMPTY, request, FooResponse.class));
  }

  @Test
  void get_whenWithNullRequest_shouldThrowException() {

    assertThrows(IllegalArgumentException.class, () -> unitUnderTest.get(URL, null, FooResponse.class));
  }

  @Test
  void get_whenWithNullResponseClass_shouldThrowException() {

    assertThrows(IllegalArgumentException.class, () -> unitUnderTest.get(URL, request, null));
  }

  @Test
  void get_whenWithConnectorException_shouldThrowApiException() {
    when(connector.get(any(RequestContext.class))).thenThrow(ConnectorException.class);

    assertThrows(ExecutionException.class, () -> unitUnderTest.get(URL, request, FooResponse.class).get());
  }

  @Test
  void get_whenWithException_shouldThrowIllegalStateException() {
    when(connector.get(any(RequestContext.class))).thenThrow(IllegalStateException.class);

    assertThrows(ExecutionException.class, () -> unitUnderTest.get(URL, request, FooResponse.class).get());
  }

  @Test
  void get_whenWithValidParameters_shouldReturnResponse() throws ExecutionException, InterruptedException {
    FooResponse expected = new FooResponse();
    RequestContext
        requestContext =
        new RequestContext("urlpart", API_KEY, request,
            FooResponse.class);
    when(connector.get(argThat(createRequestContextArgumentMatcher(requestContext)))).thenReturn(expected);

    FooResponse actual = unitUnderTest.get(PART, request, FooResponse.class).get();

    assertEquals(expected, actual);
  }

  private ArgumentMatcher<RequestContext> createRequestContextArgumentMatcher(RequestContext expected) {
    return actual -> {
      assertNotNull(actual);
      assertEquals(expected.getApiKey(), actual.getApiKey());
      assertEquals(expected.getUrl(), actual.getUrl());
      assertEquals(expected.getResponseClass(), actual.getResponseClass());
      return true;
    };
  }

}