package brawljars.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import brawljars.common.Callback;
import brawljars.common.PaginationRequest;
import brawljars.common.PaginationResponse;
import brawljars.common.RawResponse;
import brawljars.connector.Connector;
import brawljars.connector.ConnectorException;
import brawljars.connector.RequestContext;
import lombok.Getter;
import lombok.Setter;

@ExtendWith(MockitoExtension.class)
class BaseApiTest {

  private static final String API_KEY = "apiKey";
  private static final String EMPTY = "";
  private static final String PART = "part";
  private static final String URL = "url";

  private BaseApi unitUnderTest;

  @Mock
  private Connector connector;

  private ApiContext apiContext;

  private PaginationRequest<FooResponse> requestWithoutCallback;

  @Getter
  @Setter
  class FooResponse extends PaginationResponse<FooRequest> {
  }

  @BeforeEach
  void setUp() {
    apiContext = new ApiContext(URL, API_KEY, connector);
    requestWithoutCallback = new FooRequest(null, 100, "after", "before");
    unitUnderTest = new BaseApi(apiContext);
  }

  @Getter
  @Setter
  class FooRequest extends PaginationRequest<FooResponse> {

    protected FooRequest(Callback<FooResponse> callback, int limit, String after,
                         String before) {
      super(callback, limit, after, before);
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
        () -> unitUnderTest.get(null, requestWithoutCallback, FooResponse.class));
  }

  @Test
  void get_whenWithEmptyPart_shouldThrowException() {

    assertThrows(IllegalArgumentException.class,
        () -> unitUnderTest.get(EMPTY, requestWithoutCallback, FooResponse.class));
  }

  @Test
  void get_whenWithNullRequest_shouldThrowException() {

    assertThrows(IllegalArgumentException.class, () -> unitUnderTest.get(URL, null, FooResponse.class));
  }

  @Test
  void get_whenWithNullResponseClass_shouldThrowException() {

    assertThrows(IllegalArgumentException.class, () -> unitUnderTest.get(URL, requestWithoutCallback, null));
  }

  @Test
  void get_whenWithConnectorException_shouldThrowApiException() {
    when(connector.get(any(RequestContext.class))).thenThrow(ConnectorException.class);

    assertThrows(ApiException.class, () -> unitUnderTest.get(URL, requestWithoutCallback, FooResponse.class));
  }

  @Test
  void get_whenWithException_shouldThrowIllegalStateException() {
    when(connector.get(any(RequestContext.class))).thenThrow(IllegalStateException.class);

    assertThrows(IllegalStateException.class, () -> unitUnderTest.get(URL, requestWithoutCallback, FooResponse.class));
  }

  @Test
  void get_whenWithValidParameters_shouldReturnResponse() {
    FooResponse expected = new FooResponse();
    RequestContext
        requestContext =
        new RequestContext("urlpart?limit=100&after=after&before=before", API_KEY, requestWithoutCallback,
            FooResponse.class);
    when(connector.get(argThat(createRequestContextArgumentMatcher(requestContext)))).thenReturn(expected);

    FooResponse actual = unitUnderTest.get(PART, requestWithoutCallback, FooResponse.class);

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

  @Test
  void getLastResponse_whenCalled_shouldReturnLastResponse() {
    RawResponse expected = new RawResponse();
    when(connector.getLastRawResponse()).thenReturn(expected);

    assertEquals(expected, unitUnderTest.getLastResponse());
  }

}