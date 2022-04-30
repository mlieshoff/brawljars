package brawljars.api.intern.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  brawljars.api.intern.event.EventRotationRequest request = brawljars.api.intern.event.EventRotationRequest.builder()
      .build();
    prepare("/events/rotation", "src/test/resources/event-findEventRotation.json", request);

    brawljars.api.intern.event.EventRotationResponse actual = unitUnderTest.findEventRotation(request).get();
    brawljars.api.intern.event.EventRotationResponse expected = toJson(brawljars.api.intern.event.EventRotationResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}