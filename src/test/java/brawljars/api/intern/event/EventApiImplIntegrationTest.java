package brawljars.api.intern.event;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class EventApiImplIntegrationTest extends IntegrationTestBase {

  private EventApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(EventApi.class);
  }

  @Test
  void findEventRotation() throws Exception {
    brawljars.api.intern.event.EventRotationRequest.EventRotationRequestBuilder builder = brawljars.api.intern.event.EventRotationRequest.builder();
    brawljars.api.intern.event.EventRotationRequest request = builder
      .storeRawResponse(true)
      .build();
    prepare("/events/rotation", "src/test/resources/event-findEventRotation.json", request);
    brawljars.api.intern.event.EventRotationResponse expected = toJson(brawljars.api.intern.event.EventRotationResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findEventRotation(request).get());
  }

  @Test
  void findEventRotation_whenWithException() {
    brawljars.api.intern.event.EventRotationRequest.EventRotationRequestBuilder builder = brawljars.api.intern.event.EventRotationRequest.builder();
    brawljars.api.intern.event.EventRotationRequest request = builder
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/events/rotation", request, () -> unitUnderTest.findEventRotation(request).get());
  }

}