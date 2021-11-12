package brawljars.api;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import brawljars.common.IResponse;
import brawljars.common.Request;
import brawljars.connector.Connector;
import brawljars.connector.ConnectorException;
import brawljars.connector.RequestContext;
import lombok.Getter;
import lombok.Setter;

@ExtendWith(MockitoExtension.class)
class BaseApiTest {

  private static final String EMPTY = "";
  private static final String PART = "part";
  private static final String URL = "url";

  @InjectMocks
  private BaseApi unitUnderTest;

  @Mock
  private ApiContext apiContext;

  @Mock
  private Request<FooResponse> request;

  @Mock
  private Connector connector;

  @BeforeEach
  void setUp() {

  }

  @Test
  void get_whenWithNullPart_shouldThrowException() {

    assertThrows(IllegalArgumentException.class, () -> unitUnderTest.get(null, request, FooResponse.class));
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
    when(apiContext.getConnector()).thenReturn(connector);
    when(connector.get(any(RequestContext.class))).thenThrow(ConnectorException.class);

    assertThrows(ApiException.class, () -> unitUnderTest.get(URL, request, FooResponse.class));
  }

  @Test
  void get_whenWithException_shouldThrowIllegalStateException() {
    when(apiContext.getConnector()).thenReturn(connector);
    when(connector.get(any(RequestContext.class))).thenThrow(IllegalStateException.class);

    assertThrows(IllegalStateException.class, () -> unitUnderTest.get(URL, request, FooResponse.class));
  }

  @Getter
  @Setter
  class FooResponse implements IResponse {

    private String message;
    private String reason;

  }

}