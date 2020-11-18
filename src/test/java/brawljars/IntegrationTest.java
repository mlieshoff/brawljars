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

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;
import brawljars.request.GetClubMembersRequest;
import brawljars.request.GetClubRequest;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.request.GetRankingsBrawlerRequest;
import brawljars.request.GetRankingsClubsRequest;
import brawljars.request.GetRankingsPlayersRequest;
import brawljars.request.GetRankingsPowerplaySeasonsRequest;
import brawljars.request.GetRankingsPowerplaySeasonsSeasonRequest;
import brawljars.request.Request;
import brawljars.response.GetClubMembersResponse;
import brawljars.response.GetClubResponse;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;
import brawljars.response.GetRankingsBrawlerResponse;
import brawljars.response.GetRankingsClubsResponse;
import brawljars.response.GetRankingsPlayersResponse;
import brawljars.response.GetRankingsPowerplaySeasonsResponse;
import brawljars.response.GetRankingsPowerplaySeasonsSeasonResponse;
import brawljars.response.IResponse;

/**
 * @author Michael Lieshoff
 */
class IntegrationTest {

  private static final int PORT = 50000;

  static final String API_KEY = "itsasecret";
  private static final String APP = "brawljars";
  private static final String BRAWLER_ID = "brawlerId";
  private static final String CLUB_TAG = "playerTag";
  private static final String COUNTRY_CODE = "countryCode";
  private static final String CONTEXT = "test";
  private static final String PLAYER_TAG = "playerTag";
  private static final String SEASON_ID = "seasonId";
  private static final String URL = format("http://localhost:50000/%s/%s/", CONTEXT, APP);

  private static JettyServer jettyServer;

  @BeforeAll
  static void beforeClass() throws Exception {
    jettyServer = new JettyServer(PORT, '/' + CONTEXT);
    jettyServer.addServlet('/' + APP + "/players/*", new TestPlayersServlet());
    jettyServer.addServlet('/' + APP + "/clubs/*", new TestClubsServlet());
    jettyServer.addServlet('/' + APP + "/rankings/*", new TestRankingsServlet());
    jettyServer.start();
  }

  @AfterAll
  static void afterClass() throws Exception {
    jettyServer.stop();
  }

  @Test
  void getPlayer() throws Exception {

    runTest("getPlayer", "src/test/resources/player.json", GetPlayerRequest.builder(PLAYER_TAG).build(),
        GetPlayerResponse.class);
  }

  private <T extends Request<R>, R extends IResponse> void runTest(String methodName, String filename, T request,
                                                                   Class<R> responseClass) throws Exception {

    Method apiMethod = Api.class.getDeclaredMethod(methodName, request.getClass());

    assertAll(
        () -> whenWithValidParameters_thenReturnResponse(apiMethod, filename, request, responseClass),
        () -> whenWithWrongApiKey_thenThrowException(apiMethod, request)
    );

  }

  private <T extends Request<R>, R extends IResponse> void whenWithValidParameters_thenReturnResponse(Method apiMethod,
                                                                                                      String filename,
                                                                                                      T request,
                                                                                                      Class<R> responseClass)
      throws Exception {
    String expectedJson = FileUtils.readFileToString(new File(filename));
    R expected = new Gson().fromJson(expectedJson, responseClass);

    R actual = (R) apiMethod.invoke(new Api(URL, API_KEY), request);

    assertEquals(expected, actual);
  }

  private <T extends Request<R>, R extends IResponse> void whenWithWrongApiKey_thenThrowException(Method apiMethod,
                                                                                                  T request) {
    try {

      apiMethod.invoke(new Api(URL, "lala2"), request);

      fail();
    } catch (Exception e) {
      Throwable throwable = e.getCause();

      assertEquals(ApiException.class, throwable.getClass());

      ApiException apiException = (ApiException) throwable;
      assertEquals(403, apiException.getCode());
    }
  }

  @Test
  void getPlayerBattleLog() throws Exception {

    runTest("getPlayerBattleLog", "src/test/resources/playerBattleLog.json",
        GetPlayerBattleLogRequest.builder(PLAYER_TAG).build(), GetPlayerBattleLogResponse.class);
  }

  @Test
  void getClub() throws Exception {

    runTest("getClub", "src/test/resources/club.json",
        GetClubRequest.builder(CLUB_TAG).build(), GetClubResponse.class);
  }

  @Test
  void getClubMembers() throws Exception {

    runTest("getClubMembers", "src/test/resources/clubMembers.json",
        GetClubMembersRequest.builder(CLUB_TAG).build(), GetClubMembersResponse.class);
  }

  @Test
  void getRankingsPowerplaySeasons() throws Exception {

    runTest("getRankingsPowerplaySeasons", "src/test/resources/rankingsPowerplaySeasons.json",
        GetRankingsPowerplaySeasonsRequest.builder(COUNTRY_CODE).build(), GetRankingsPowerplaySeasonsResponse.class);
  }

  @Test
  void getRankingsPowerplaySeasonsSeason() throws Exception {

    runTest("getRankingsPowerplaySeasonsSeason", "src/test/resources/rankingsPowerplaySeasonsSeason.json",
        GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID).build(),
        GetRankingsPowerplaySeasonsSeasonResponse.class);
  }

  @Test
  void getRankingsClubs() throws Exception {

    runTest("getRankingsClubs", "src/test/resources/rankingsClubs.json",
        GetRankingsClubsRequest.builder(COUNTRY_CODE).build(), GetRankingsClubsResponse.class);
  }

  @Test
  void getRankingsBrawler() throws Exception {

    runTest("getRankingsBrawler", "src/test/resources/rankingsBrawler.json",
        GetRankingsBrawlerRequest.builder(COUNTRY_CODE, BRAWLER_ID).build(), GetRankingsBrawlerResponse.class);
  }

  @Test
  void getRankingsPlayers() throws Exception {

    runTest("getRankingsPlayers", "src/test/resources/rankingsPlayers.json",
        GetRankingsPlayersRequest.builder(COUNTRY_CODE).build(), GetRankingsPlayersResponse.class);
  }

}
