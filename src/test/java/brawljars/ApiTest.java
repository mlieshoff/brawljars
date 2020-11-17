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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import brawljars.request.GetClubMembersRequest;
import brawljars.request.GetClubRequest;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.request.GetRankingsPowerplaySeasonsRequest;
import brawljars.request.GetRankingsPowerplaySeasonsSeasonRequest;
import brawljars.request.Request;
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
class ApiTest {

  public static final String API_KEY = "apiKey";
  public static final String CLUB_TAG = "clubTag";
  public static final String COUNTRY_CODE = "countryCode";
  public static final String SEASON_ID = "seasonId";
  public static final String PLAYER_TAG = "playerTag";
  public static final String URL = "url";

  private Client client;

  private Api api;

  private CrawlerException crawlerException;
  private RuntimeException runtimeException;

  @BeforeEach
  void setUp() throws Exception {
    ClientFactory clientFactory = mock(ClientFactory.class);
    client = mock(Client.class);
    when(clientFactory.createClient(URL, API_KEY)).thenReturn(client);
    api = new Api(URL, API_KEY, clientFactory);
    crawlerException = mock(CrawlerException.class);
    when(crawlerException.getStatusCode()).thenReturn(SC_NOT_FOUND);
    runtimeException = new IllegalStateException(crawlerException);
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
  void getPlayer() throws Exception {

    runTest("getPlayer", GetPlayerRequest.builder(PLAYER_TAG).build(), new GetPlayerResponse());
  }

  private <T extends Request<R>, R extends IResponse> void runTest(String methodName, T request, R response)
      throws Exception {
    Method clientMethod = client.getClass().getDeclaredMethod(methodName, request.getClass());
    Method apiMethod = api.getClass().getDeclaredMethod(methodName, request.getClass());

    assertAll(
        () -> whenWithNullRequest_thenThrowsException(apiMethod),
        () -> whenWithRequest_thenReturnResult(apiMethod, clientMethod, request, response),
        () -> whenWithException_thenThrowApiException(apiMethod, clientMethod, request)
    );
  }

  <T extends Request<R>, R extends IResponse> void whenWithNullRequest_thenThrowsException(Method apiMethod) {
    try {
      apiMethod.invoke(api, (T) null);

      fail();
    } catch (Exception e) {

      assertEquals(NullPointerException.class, e.getCause().getClass());
    }
  }

  private <T extends Request<R>, R extends IResponse> void whenWithRequest_thenReturnResult(Method apiMethod,
                                                                                            Method clientMethod,
                                                                                            T request, R response)
      throws InvocationTargetException, IllegalAccessException {
    when(clientMethod.invoke(client, request)).thenReturn(response);

    assertEquals(response, apiMethod.invoke(api, request));
  }

  private <T extends Request<R>, R extends IResponse> void whenWithException_thenThrowApiException(Method apiMethod,
                                                                                                   Method clientMethod,
                                                                                                   T request)
      throws InvocationTargetException, IllegalAccessException {
    when(clientMethod.invoke(client, request)).thenThrow(runtimeException);
    try {
      apiMethod.invoke(api, request);

      fail();
    } catch (Exception e) {
      ApiException apiException = (ApiException) e.getCause();
      assertEquals(SC_NOT_FOUND, apiException.getCode());
    }
  }

  @Test
  void getPlayerBattleLog() throws Exception {

    runTest("getPlayerBattleLog", GetPlayerBattleLogRequest.builder(PLAYER_TAG).build(),
        new GetPlayerBattleLogResponse());
  }

  @Test
  void getClub_whenWithNullRequest_thenThrowsException() throws Exception {

    assertThrows(NullPointerException.class, () -> api.getClub(null));
  }

  @Test
  void getClub() throws Exception {

    runTest("getClub", GetClubRequest.builder(CLUB_TAG).build(), new GetClubResponse());
  }

  @Test
  void getClubMembers() throws Exception {

    runTest("getClubMembers", GetClubMembersRequest.builder(CLUB_TAG).build(), new GetClubMembersResponse());
  }

  @Test
  void getRankingsPowerplaySeasons() throws Exception {

    runTest("getRankingsPowerplaySeasons", GetRankingsPowerplaySeasonsRequest.builder(COUNTRY_CODE).build(),
        new GetRankingsPowerplaySeasonsResponse());
  }

  @Test
  void getRankingsPowerplaySeasonsSeason() throws Exception {

    runTest("getRankingsPowerplaySeasonsSeason",
        GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID).build(),
        new GetRankingsPowerplaySeasonsSeasonResponse());
  }

}
