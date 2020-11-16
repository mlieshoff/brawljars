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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.request.GetClubRequest;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.response.IResponse;

/**
 * @author Michael Lieshoff
 */
class EndToEnd {

  private static final String API_KEY = System.getProperty("api.key");

  private Api api;

  @BeforeEach
  void setUp() throws Exception {
    api = new Api("https://bsproxy.royaleapi.dev/v1/", API_KEY);
  }

  @Test
  void getPlayer_whenWithValidParameters_thenGetResponse() throws Exception {

    assertResponse(api.getPlayer(GetPlayerRequest.builder("#28UP80RRY").build()));
  }

  private void assertResponse(IResponse response) {
    assertAll(
        () -> assertNotNull(response, "response"),
        () -> assertNull(response.getMessage(), "message"),
        () -> assertNull(response.getReason(), "reason")
    );
  }

  @Test
  void getPlayerBattleLog_whenWithValidParameters_thenGetResponse() throws Exception {

    assertResponse(api.getPlayerBattleLog(GetPlayerBattleLogRequest.builder("#28UP80RRY").build()));
  }

  @Test
  void getClub_whenWithValidParameters_thenGetResponse() throws Exception {

    assertResponse(api.getClub(GetClubRequest.builder("#L99U2L2").build()));
  }

}
