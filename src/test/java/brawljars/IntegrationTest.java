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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import brawljars.request.GetPlayerRequest;
import brawljars.response.GetPlayerResponse;

/**
 * @author Michael Lieshoff
 */
class IntegrationTest {

  private static final int PORT = 50000;

  private static JettyServer jettyServer;

  private static final String CONTEXT = "test";
  private static final String APP = "brawljars";
  private static final String URL = String.format("http://localhost:50000/%s/%s/", CONTEXT, APP);
  static final String API_KEY = "itsasecret";
  private static final String PLAYER_TAG = "playerTag";

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

  @Test
  void getPlayer_whenWithValidParameters_thenReturnResponse() throws Exception {
    doGetPlayer(API_KEY);
  }

  private static void doGetPlayer(String apiKey) {
    GetPlayerResponse actual = new Api(URL, apiKey).getPlayer(GetPlayerRequest.builder(PLAYER_TAG).build());

    assertNotNull(actual);
  }

  @Test
  void getPlayer_whenWithWrongUrl_thenThrow() throws Exception {

    assertThrows(ApiException.class, () -> doGetPlayer("lala2"));
  }

}
