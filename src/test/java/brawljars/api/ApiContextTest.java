package brawljars.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import brawljars.connector.Connector;

@ExtendWith(MockitoExtension.class)
class ApiContextTest {

  private static final String API_KEY = "apiKey";
  private static final String EMPTY = "";
  private static final String URL = "url";

  @Mock
  private Connector connector;

  @Test
  void construct_whenWithNullUrl_throwException() {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(null, API_KEY, connector));
  }

  @Test
  void construct_whenWithEmptyUrl_throwException() {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(EMPTY, API_KEY, connector));
  }

  @Test
  void construct_whenWithNullApiKey_throwException() {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(URL, null, connector));
  }

  @Test
  void construct_whenWithEmptyApiKey_throwException() {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(URL, EMPTY, connector));
  }

  @Test
  void construct_whenWithNullConnector_throwException() {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(URL, API_KEY, null));
  }

  @Test
  void construct_whenWithParameters_shouldSetValues() {
    ApiContext actual = new ApiContext(URL, API_KEY, connector);

    assertEquals(URL, actual.getUrl());
    assertEquals(API_KEY, actual.getApiKey());
    assertEquals(connector, actual.getConnector());
  }

}