/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package brawljars.api.intern.events;

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
    brawljars.api.intern.events.EventRotationRequest.EventRotationRequestBuilder
        builder =
        brawljars.api.intern.events.EventRotationRequest.builder();
    brawljars.api.intern.events.EventRotationRequest request = builder

        .storeRawResponse(true)
        .build();
    prepare("/events/rotation", EMPTY, "src/test/resources/event-findEventRotation.json", request);
    brawljars.api.intern.events.EventRotationResponse
        expected =
        toJson(brawljars.api.intern.events.EventRotationResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findEventRotation(request).get());
  }

  @Test
  void findEventRotation_whenWithException() {
    brawljars.api.intern.events.EventRotationRequest.EventRotationRequestBuilder
        builder =
        brawljars.api.intern.events.EventRotationRequest.builder();
    brawljars.api.intern.events.EventRotationRequest request = builder

        .storeRawResponse(true)
        .build();

    prepareWithErrorAndRun("/events/rotation", EMPTY, request, () -> unitUnderTest.findEventRotation(request).get());
  }

}