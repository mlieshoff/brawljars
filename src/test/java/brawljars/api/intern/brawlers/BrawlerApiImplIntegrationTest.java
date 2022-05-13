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
package brawljars.api.intern.brawlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class BrawlerApiImplIntegrationTest extends IntegrationTestBase {

  private BrawlerApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(BrawlerApi.class);
  }

  @Test
  void findAll() throws Exception {
    brawljars.api.intern.brawlers.BrawlersRequest.BrawlersRequestBuilder builder = brawljars.api.intern.brawlers.BrawlersRequest.builder();
    brawljars.api.intern.brawlers.BrawlersRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/brawlers", "src/test/resources/brawler-findAll.json", request);
    brawljars.api.intern.brawlers.BrawlersResponse expected = toJson(brawljars.api.intern.brawlers.BrawlersResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findAll(request).get());
  }

  @Test
  void findAll_whenWithException() {
    brawljars.api.intern.brawlers.BrawlersRequest.BrawlersRequestBuilder builder = brawljars.api.intern.brawlers.BrawlersRequest.builder();
    brawljars.api.intern.brawlers.BrawlersRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/brawlers", request, () -> unitUnderTest.findAll(request).get());
  }

  @Test
  void findById() throws Exception {
    long brawlerId = 4711L;
    brawljars.api.intern.brawlers.BrawlerRequest.BrawlerRequestBuilder builder = brawljars.api.intern.brawlers.BrawlerRequest.builder(brawlerId);
    brawljars.api.intern.brawlers.BrawlerRequest request = builder
      .storeRawResponse(true)
      .build();
    prepare("/brawlers/{brawlerId}".replace("{brawlerId}", String.valueOf(brawlerId)), "src/test/resources/brawler-findById.json", request);
    brawljars.api.intern.brawlers.BrawlerResponse expected = toJson(brawljars.api.intern.brawlers.BrawlerResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findById(request).get());
  }

  @Test
  void findById_whenWithException() {
    long brawlerId = 4711L;
    brawljars.api.intern.brawlers.BrawlerRequest.BrawlerRequestBuilder builder = brawljars.api.intern.brawlers.BrawlerRequest.builder(brawlerId);
    brawljars.api.intern.brawlers.BrawlerRequest request = builder
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/brawlers/{brawlerId}".replace("{brawlerId}", String.valueOf(brawlerId)), request, () -> unitUnderTest.findById(request).get());
  }

}