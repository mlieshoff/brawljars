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
package brawljars;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import brawljars.response.RawResponse;

/**
 * @author Michael Lieshoff
 */
class ClientTest {

  private static final String API_KEY = "apiKey";
  private static final String URL = "url";

  private CrawlerFactory crawlerFactory;

  private Crawler crawler;

  @BeforeEach
  void setUp() throws Exception {
    crawlerFactory = mock(CrawlerFactory.class);
    crawler = mock(Crawler.class);
    when(crawlerFactory.createCrawler()).thenReturn(crawler);
  }

  @Test
  void construct_whenWithNullUrl_thenThrowException() throws Exception {
    assertThrows(NullPointerException.class, () -> new Client(null, API_KEY, crawlerFactory));
  }

  @Test
  void construct_whenWithEmptyUrl_thenThrowException() throws Exception {
    assertThrows(IllegalArgumentException.class, () -> new Client("", API_KEY, crawlerFactory));
  }

  @Test
  void construct_whenWithNullCrawlerFactory_thenThrowException() throws Exception {
    assertThrows(NullPointerException.class, () -> new Client(URL, API_KEY, null));
  }

  private Client createClient() {
    return new Client("lala/", API_KEY, crawlerFactory);
  }

  private static Map<String, String> createHeaders() {
    return ImmutableMap.<String, String>builder().put(AUTHORIZATION, "Bearer " + API_KEY).build();
  }

  @Test
  void getLastRawResponse_whenCalled_thenReturnLastRawResponse() throws Exception {
    RawResponse rawResponse = new RawResponse();
    when(crawler.getLastRawResponse()).thenReturn(rawResponse);
    assertEquals(rawResponse, createClient().getLastRawResponse());
  }

  private static void waitUntil(Action action) {
    long stop = System.currentTimeMillis() + 5000L;
    for (long ms = System.currentTimeMillis(); ms < stop; ms = System.currentTimeMillis()) {
      if (action.eval()) {
        break;
      }
    }
  }

  private interface Action {

    boolean eval();

  }

}