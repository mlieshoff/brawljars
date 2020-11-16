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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;

/**
 * @author Michael Lieshoff
 */
class IntegrationTest {

  static final String API_KEY = "itsasecret";
  private static final int PORT = 50000;
  private static final String CONTEXT = "test";
  private static final String APP = "brawljars";
  private static final String URL = format("http://localhost:50000/%s/%s/", CONTEXT, APP);
  private static final String PLAYER_TAG = "playerTag";
  private static JettyServer jettyServer;

  @BeforeAll
  static void beforeClass() throws Exception {
    jettyServer = new JettyServer(PORT, '/' + CONTEXT);
    jettyServer.addServlet('/' + APP + "/players/*", new TestPlayersServlet());
    jettyServer.start();
  }

  @AfterAll
  static void afterClass() throws Exception {
    jettyServer.stop();
  }

  private static void doGetPlayer(String apiKey) throws IOException {
    String expectedJson = FileUtils.readFileToString(new File("src/test/resources/player.json"));
    GetPlayerResponse expected = new Gson().fromJson(expectedJson, GetPlayerResponse.class);

    GetPlayerResponse actual = new Api(URL, apiKey).getPlayer(GetPlayerRequest.builder(PLAYER_TAG).build());

    assertEquals(expected, actual);
  }

  private static void doGetPlayerBattleLog(String apiKey) throws IOException {
    String expectedJson = FileUtils.readFileToString(new File("src/test/resources/playerBattleLog.json"));
    GetPlayerBattleLogResponse expected = new Gson().fromJson(expectedJson, GetPlayerBattleLogResponse.class);

    GetPlayerBattleLogResponse
        actual = new Api(URL, apiKey).getPlayerBattleLog(GetPlayerBattleLogRequest.builder(PLAYER_TAG).build());

    assertEquals(expected, actual);
  }

  @Test
  void getPlayer_whenWithValidParameters_thenReturnResponse() throws Exception {
    doGetPlayer(API_KEY);
  }

  @Test
  void getPlayer_whenWithWrongUrl_thenThrow() throws Exception {

    assertThrows(ApiException.class, () -> doGetPlayer("lala2"));
  }

  @Test
  void getPlayerBattleLog_whenWithValidParameters_thenReturnResponse() throws Exception {
    doGetPlayerBattleLog(API_KEY);
  }

  @Test
  void getPlayerBattleLog_whenWithWrongUrl_thenThrow() throws Exception {

    assertThrows(ApiException.class, () -> doGetPlayerBattleLog("lala2"));
  }

}
