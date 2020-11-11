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

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.request.GetPlayerRequest;
import brawljars.response.GetPlayerResponse;
import brawljars.response.RawResponse;

/**
 * @author Michael Lieshoff
 */
class ApiTest {

  public static final String API_KEY = "apiKey";
  public static final String PLAYER_TAG = "playerTag";
  public static final String URL = "url";

  private Client client;

  private Api api;

  private CrawlerException crawlerException;

  @BeforeEach
  void setUp() throws Exception {
    ClientFactory clientFactory = mock(ClientFactory.class);
    client = mock(Client.class);
    when(clientFactory.createClient(URL, API_KEY)).thenReturn(client);
    api = new Api(URL, API_KEY, clientFactory);
    crawlerException = mock(CrawlerException.class);
    when(crawlerException.getStatusCode()).thenReturn(SC_NOT_FOUND);
  }

  @Test
  void construct_whenWithNullUrl_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> new Api(null, API_KEY));
  }

  @Test
  void construct_whenWithEmptyUrl_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> new Api("", API_KEY));
  }

  @Test
  void construct_whenWithNullApiKey_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> new Api(URL, null));
  }

  @Test
  void construct_whenWithEmptyApiKey_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> new Api(URL, ""));
  }

  @Test
  void getLastRawResponse_whenCalled_theReturnLastRawResponse() throws Exception {
    RawResponse rawResponse = new RawResponse();
    when(client.getLastRawResponse()).thenReturn(rawResponse);

    assertEquals(rawResponse, api.getLastRawResponse());
  }

  @Test
  void getPlayer_whenWithNullRequest_thenThrowsException() throws Exception {

    assertThrows(NullPointerException.class, () -> api.getPlayer(null));
  }

  @Test
  void getPlayer_whenWithRequest_thenReturnResult() throws Exception {
    GetPlayerRequest getPlayerRequest = GetPlayerRequest.builder(PLAYER_TAG).build();
    GetPlayerResponse getPlayerResponse = new GetPlayerResponse();
    when(client.getPlayer(getPlayerRequest)).thenReturn(getPlayerResponse);

    assertEquals(getPlayerResponse, api.getPlayer(getPlayerRequest));
  }

  @Test
  void getPlayer_whenWithException_thenThrowApiException() throws Exception {
    GetPlayerRequest getPlayerRequest = GetPlayerRequest.builder(PLAYER_TAG).build();
    when(client.getPlayer(getPlayerRequest)).thenThrow(crawlerException);
    try {
      api.getPlayer(getPlayerRequest);

      fail();
    } catch (ApiException e) {

      assertEquals(SC_NOT_FOUND, e.getCode());
    }
  }

}
