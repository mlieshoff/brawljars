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
import brawljars.response.GetClubMembersResponse;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;
import brawljars.response.GetRankingsPowerplaySeasonsResponse;
import brawljars.response.GetRankingsPowerplaySeasonsSeasonResponse;
import brawljars.response.IResponse;

class JsonModelIntegrationTest {

  private static final Gson GSON = new Gson();

  @Test
  void getPlayer() throws Exception {

    runTest("src/test/resources/player.json", GetPlayerResponse.class);
  }

  <R extends IResponse> void runTest(String filename, Class<R> responseClass) throws Exception {
    String json = readFileToString(new File(filename));

    assertNotNull(GSON.fromJson(json, responseClass));
  }

  @Test
  void getPlayerBattleLog() throws Exception {

    runTest("src/test/resources/playerBattleLog.json", GetPlayerBattleLogResponse.class);
  }

  @Test
  void getClub() throws Exception {

    runTest("src/test/resources/club.json", GetClubMembersResponse.class);
  }

  @Test
  void getClubMembers() throws Exception {

    runTest("src/test/resources/clubMembers.json", GetClubMembersResponse.class);
  }

  @Test
  void getRankingsPowerplaySeasons() throws Exception {

    runTest("src/test/resources/rankingsPowerplaySeasons.json", GetRankingsPowerplaySeasonsResponse.class);
  }

  @Test
  void getRankingsPowerplaySeasonsSeason() throws Exception {

    runTest("src/test/resources/rankingsPowerplaySeasonsSeason.json", GetRankingsPowerplaySeasonsSeasonResponse.class);
  }

}
