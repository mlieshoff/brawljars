package brawljars.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import brawljars.connector.Connector;

@ExtendWith(MockitoExtension.class)
class ApiContextTest {

  private static final String API_KEY = "apiKey";
  private static final String URL = "url";

  @Mock
  private Connector connector;

  @ParameterizedTest
  @CsvSource(value = "null,", nullValues = "null")
  void construct_withoutUrl_shouldThrowException(String actual) {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(actual, API_KEY, connector));
  }

  @ParameterizedTest
  @CsvSource(value = "null,", nullValues = "null")
  void construct_withoutApiKey_shouldThrowException(String actual) {

    assertThrows(IllegalArgumentException.class, () -> new ApiContext(URL, actual, connector));
  }

  @Test
  void construct_withoutConnector_shouldThrowException() {

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