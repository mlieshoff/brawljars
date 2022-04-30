package brawljars.api.intern.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;
import brawljars.common.BlockingCallback;

public class EventApiImplIntegrationTest extends IntegrationTestBase {

  private EventApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(EventApi.class);
  }

  @Test
  void findEventRotation() throws Exception {
    run_findEventRotation(false);
  }

  void run_findEventRotation(boolean withCallback) throws Exception {
    brawljars.api.intern.event.EventRotationRequest.EventRotationRequestBuilder builder = brawljars.api.intern.event.EventRotationRequest.builder();
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.event.EventRotationRequest request = builder.build();
    prepare("/events/rotation", "src/test/resources/event-findEventRotation.json", request);

    brawljars.api.intern.event.EventRotationResponse actual;
    if (withCallback) {
      unitUnderTest.findEventRotation(request);
      actual = ((BlockingCallback<brawljars.api.intern.event.EventRotationResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findEventRotation(request);
    }
    brawljars.api.intern.event.EventRotationResponse expected = toJson(brawljars.api.intern.event.EventRotationResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findEventRotation_withCallback() throws Exception {
    run_findEventRotation(true);
  }

}