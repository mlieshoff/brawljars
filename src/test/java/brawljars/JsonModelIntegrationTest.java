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

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;

import java.io.File;
import brawljars.response.GetClubResponse;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;

class JsonModelIntegrationTest {

  private static final Gson GSON = new Gson();

  @Test
  void fromJson_whenForGetPlayer_thenResolve() throws Exception {
    String json = readFileToString(new File("src/test/resources/player.json"));
    GetPlayerResponse object = GSON.fromJson(json, GetPlayerResponse.class);

    assertNotNull(object);
  }

  @Test
  void fromJson_whenForGetPlayerBattleLog_thenResolve() throws Exception {
    String json = readFileToString(new File("src/test/resources/playerBattleLog.json"));
    GetPlayerBattleLogResponse object = GSON.fromJson(json, GetPlayerBattleLogResponse.class);

    assertNotNull(object);
  }

  @Test
  void fromJson_whenForGetClub_thenResolve() throws Exception {
    String json = readFileToString(new File("src/test/resources/club.json"));
    GetClubResponse object = GSON.fromJson(json, GetClubResponse.class);

    assertNotNull(object);
  }

}
