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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import brawljars.request.GetClubMembersRequest;
import brawljars.request.GetClubRequest;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.request.GetRankingsPowerplaySeasonsRequest;
import brawljars.request.GetRankingsPowerplaySeasonsSeasonRequest;
import brawljars.request.Request;
import brawljars.response.Callback;
import brawljars.response.GetClubMembersResponse;
import brawljars.response.GetClubResponse;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;
import brawljars.response.GetRankingsPowerplaySeasonsResponse;
import brawljars.response.GetRankingsPowerplaySeasonsSeasonResponse;
import brawljars.response.IResponse;
import brawljars.response.RawResponse;

/**
 * @author Michael Lieshoff
 */
class ClientTest {

  public static final String API_KEY = "apiKey";
  public static final String BASE_URL = "lala/";
  public static final String CLUB_TAG = "clubTag";
  public static final String COUNTRY_CODE = "countryCode";
  public static final String SEASON_ID = "seasonId";
  public static final String URL = "url";
  public static final String PLAYER_TAG = "playerTag";

  private final AtomicBoolean state = new AtomicBoolean();

  private CrawlerFactory crawlerFactory;

  private Crawler crawler;

  @BeforeEach
  void setUp() throws Exception {
    state.set(false);
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
    return new Client(BASE_URL, API_KEY, crawlerFactory);
  }

  @Test
  void getLastRawResponse_whenCalled_thenReturnLastRawResponse() throws Exception {
    RawResponse rawResponse = new RawResponse();
    when(crawler.getLastRawResponse()).thenReturn(rawResponse);

    assertEquals(rawResponse, createClient().getLastRawResponse());
  }

  @Test
  void getPlayer() throws Exception {

    runTest("getPlayer", "players/%s", GetPlayerRequest.builder(PLAYER_TAG).build(), new GetPlayerResponse());
  }

  private <T extends Request<R>, R extends IResponse> void runTest(String methodName, String urlPart, T request,
                                                                   R response) throws Exception {
    Method clientMethod = Client.class.getDeclaredMethod(methodName, request.getClass());

    assertAll(
        () -> whenWithRequest_thenGetResponse(clientMethod, urlPart, request, response),
        () -> whenWithRequestAndException_thenThrowException(clientMethod, urlPart, request),
        () -> whenWithCallback_onResponse(clientMethod, urlPart, request, response),
        () -> whenWithCallback_onException(clientMethod, urlPart, request)
    );
  }

  private <T extends Request<R>, R extends IResponse> void whenWithRequest_thenGetResponse(Method clientMethod,
                                                                                           String urlPart, T request,
                                                                                           R response)
      throws Exception {
    reset(crawler);
    when(crawler.get(BASE_URL + urlPart, createHeaders(), request.getQueryParameters(), request.getRestParameters()))
        .thenReturn("{}");

    assertEquals(response, clientMethod.invoke(createClient(), request));
  }

  private static Map<String, String> createHeaders() {
    return ImmutableMap.<String, String>builder().put(AUTHORIZATION, "Bearer " + API_KEY).build();
  }

  private <T extends Request<R>, R extends IResponse> void whenWithRequestAndException_thenThrowException(
      Method clientMethod, String urlPart, T request) throws Exception {
    reset(crawler);
    when(crawler.get(BASE_URL + urlPart, createHeaders(), request.getQueryParameters(), request.getRestParameters()))
        .thenThrow(new IOException());

    try {
      clientMethod.invoke(createClient(), request);

      fail();
    } catch (Exception e) {
      Throwable throwable = e.getCause();

      assertEquals(IllegalStateException.class, throwable.getClass());
      assertEquals(IOException.class, throwable.getCause().getClass());
    }
  }

  private <T extends Request<R>, R extends IResponse> void whenWithCallback_onResponse(Method clientMethod,
                                                                                       String urlPart, T request,
                                                                                       R response) throws Exception {
    reset(crawler);
    when(crawler.get(BASE_URL + urlPart, createHeaders(), request.getQueryParameters(), request.getRestParameters()))
        .thenReturn("{}");
    whenWithCallback(clientMethod, request, new Callback() {
      @Override
      public void onResponse(IResponse result) {

        assertEquals(response, result);

        state.set(true);
      }

      @Override
      public void onException(Exception exception) {

        fail();
      }
    });
  }

  private <T extends Request<R>, R extends IResponse> void whenWithCallback(Method clientMethod, T request,
                                                                            Callback<?> callback) throws Exception {
    Field callbackField = Request.class.getDeclaredField("callback");
    callbackField.setAccessible(true);
    callbackField.set(request, callback);

    clientMethod.invoke(createClient(), request);

    waitUntil(state::get);
    assertTrue(state.get(), "callback not notified!");
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

  private <T extends Request<R>, R extends IResponse> void whenWithCallback_onException(Method clientMethod,
                                                                                        String urlPart, T request)
      throws Exception {
    reset(crawler);
    when(crawler.get(BASE_URL + urlPart, createHeaders(), request.getQueryParameters(), request.getRestParameters()))
        .thenThrow(new IOException());
    whenWithCallback(clientMethod, request, new Callback() {
      @Override
      public void onResponse(IResponse result) {

        fail();
      }

      @Override
      public void onException(Exception exception) {
        state.set(true);
      }
    });
  }

  @Test
  void getPlayerBattleLog() throws Exception {

    runTest("getPlayerBattleLog", "players/%s/battlelog", GetPlayerBattleLogRequest.builder(PLAYER_TAG).build(),
        new GetPlayerBattleLogResponse());
  }

  @Test
  void getClub() throws Exception {

    runTest("getClub", "clubs/%s", GetClubRequest.builder(CLUB_TAG).build(), new GetClubResponse());
  }

  @Test
  void getClubMembers() throws Exception {

    runTest("getClubMembers", "clubs/%s/members", GetClubMembersRequest.builder(CLUB_TAG).build(),
        new GetClubMembersResponse());
  }

  @Test
  void getRankingsPowerplaySeasons() throws Exception {

    runTest("getRankingsPowerplaySeasons", "rankings/%s/powerplay/seasons",
        GetRankingsPowerplaySeasonsRequest.builder(COUNTRY_CODE).build(), new GetRankingsPowerplaySeasonsResponse());
  }

  @Test
  void getRankingsPowerplaySeasonsSeason() throws Exception {

    runTest("getRankingsPowerplaySeasonsSeason", "rankings/%s/powerplay/seasons/%s",
        GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID).build(),
        new GetRankingsPowerplaySeasonsSeasonResponse());
  }

}