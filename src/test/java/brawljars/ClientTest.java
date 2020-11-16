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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import brawljars.request.GetClubMembersRequest;
import brawljars.request.GetClubRequest;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.response.Callback;
import brawljars.response.GetPlayerResponse;
import brawljars.response.RawResponse;

/**
 * @author Michael Lieshoff
 */
class ClientTest {

  public static final String API_KEY = "apiKey";
  public static final String CLUB_TAG = "clubTag";
  public static final String URL = "url";
  public static final String PLAYER_TAG = "playerTag";

  private CrawlerFactory crawlerFactory;

  private Crawler crawler;

  private static Map<String, String> createHeaders() {
    return ImmutableMap.<String, String>builder().put(AUTHORIZATION, "Bearer " + API_KEY).build();
  }

  private static void waitUntil(Action action) {
    long stop = System.currentTimeMillis() + 5000L;
    for (long ms = System.currentTimeMillis(); ms < stop; ms = System.currentTimeMillis()) {
      if (action.eval()) {
        break;
      }
    }
  }

  @FunctionalInterface
  private interface Action {

    boolean eval();

  }

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

  @Test
  void getLastRawResponse_whenCalled_thenReturnLastRawResponse() throws Exception {
    RawResponse rawResponse = new RawResponse();
    when(crawler.getLastRawResponse()).thenReturn(rawResponse);

    assertEquals(rawResponse, createClient().getLastRawResponse());
  }

  @Test
  void getPlayer_whenWithRequest_thenGetResponse() throws Exception {
    GetPlayerRequest getPlayerRequest = GetPlayerRequest.builder(PLAYER_TAG).build();
    when(crawler
        .get("lala/players/%s", createHeaders(), getPlayerRequest.getQueryParameters(),
            getPlayerRequest.getRestParameters()))
        .thenReturn("{}");

    assertNotNull(createClient().getPlayer(getPlayerRequest));
  }

  @Test
  void getPlayer_whenWithRequestAndException_thenThrowException() throws Exception {
    GetPlayerRequest getPlayerRequest = GetPlayerRequest.builder(PLAYER_TAG).build();
    when(crawler
        .get("lala/players/%s", createHeaders(), getPlayerRequest.getQueryParameters(),
            getPlayerRequest.getRestParameters()))
        .thenThrow(new IOException());

    assertThrows(IllegalStateException.class, () -> createClient().getPlayer(getPlayerRequest));
  }

  @Test
  void getPlayer_whenWithCallback_thenCallOnResponse() throws Exception {
    AtomicBoolean state = new AtomicBoolean(false);
    GetPlayerRequest
        getPlayerRequest =
        GetPlayerRequest.builder(PLAYER_TAG).callback(new Callback<GetPlayerResponse>() {
          @Override
          public void onResponse(GetPlayerResponse getPlayerResponse) {
            assertNotNull(getPlayerResponse);
            state.set(true);
          }

          @Override
          public void onException(Exception exception) {
            fail();
          }
        }).build();
    when(crawler.get("lala/players/%s", createHeaders(), getPlayerRequest.getQueryParameters(),
        getPlayerRequest.getRestParameters())).thenReturn("{}");
    createClient().getPlayer(getPlayerRequest);
    waitUntil(state::get);

    assertTrue(state.get(), "callback not notified!");
  }

  @Test
  void getPlayer_whenWithCallbackExpection_thenCallOnException() throws Exception {
    AtomicBoolean state = new AtomicBoolean(false);
    GetPlayerRequest
        getPlayerRequest =
        GetPlayerRequest.builder(PLAYER_TAG).callback(new Callback<GetPlayerResponse>() {
          @Override
          public void onResponse(GetPlayerResponse getPlayerResponse) {
            fail();
          }

          @Override
          public void onException(Exception exception) {
            assertNotNull(exception);
            state.set(true);
          }
        }).build();
    when(crawler.get("lala/players/%s", createHeaders(), getPlayerRequest.getQueryParameters(),
        getPlayerRequest.getRestParameters())).thenThrow(new IOException());
    createClient().getPlayer(getPlayerRequest);
    waitUntil(state::get);

    assertTrue(state.get(), "callback not notified!");
  }

  @Test
  void getPlayerBattleLog_whenWithRequest_thenGetResponse() throws Exception {
    GetPlayerBattleLogRequest getPlayerBattleLogRequest = GetPlayerBattleLogRequest.builder(PLAYER_TAG).build();
    when(crawler
        .get("lala/players/%s/battlelog", createHeaders(), getPlayerBattleLogRequest.getQueryParameters(),
            getPlayerBattleLogRequest.getRestParameters()))
        .thenReturn("{}");

    assertNotNull(createClient().getPlayerBattleLog(getPlayerBattleLogRequest));
  }

  @Test
  void getClub_whenWithRequest_thenGetResponse() throws Exception {
    GetClubRequest getClubRequest = GetClubRequest.builder(CLUB_TAG).build();
    when(crawler
        .get("lala/clubs/%s", createHeaders(), getClubRequest.getQueryParameters(), getClubRequest.getRestParameters()))
        .thenReturn("{}");

    assertNotNull(createClient().getClub(getClubRequest));
  }

  @Test
  void getClubMembers_whenWithRequest_thenGetResponse() throws Exception {
    GetClubMembersRequest getClubMembersRequest = GetClubMembersRequest.builder(CLUB_TAG).build();
    when(crawler
        .get("lala/clubs/%s/members", createHeaders(), getClubMembersRequest.getQueryParameters(),
            getClubMembersRequest.getRestParameters()))
        .thenReturn("{}");

    assertNotNull(createClient().getClubMembers(getClubMembersRequest));
  }

}