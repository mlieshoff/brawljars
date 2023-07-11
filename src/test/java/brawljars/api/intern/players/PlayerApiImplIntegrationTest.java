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
package brawljars.api.intern.players;

import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import brawljars.IntegrationTestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerApiImplIntegrationTest extends IntegrationTestBase {

    private PlayerApi unitUnderTest;

    @BeforeEach
    void setUp() {
        unitUnderTest = getBrawlJars().getApi(PlayerApi.class);
    }

    @Test
    void findById() throws Exception {
        String playerTag = "playerTag";
        brawljars.api.intern.players.info.PlayerRequest.PlayerRequestBuilder builder =
                brawljars.api.intern.players.info.PlayerRequest.builder(playerTag);
        brawljars.api.intern.players.info.PlayerRequest request =
                builder.storeRawResponse(true).build();
        prepare(
                "/players/{playerTag}".replace("{playerTag}", String.valueOf(playerTag)),
                EMPTY,
                "src/test/resources/player-findById.json",
                request);
        brawljars.api.intern.players.info.PlayerResponse expected =
                toJson(brawljars.api.intern.players.info.PlayerResponse.class, getExpected());

        run(expected, () -> unitUnderTest.findById(request).get());
    }

    @Test
    void findById_whenWithException() {
        String playerTag = "playerTag";
        brawljars.api.intern.players.info.PlayerRequest.PlayerRequestBuilder builder =
                brawljars.api.intern.players.info.PlayerRequest.builder(playerTag);
        brawljars.api.intern.players.info.PlayerRequest request =
                builder.storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/players/{playerTag}".replace("{playerTag}", String.valueOf(playerTag)),
                EMPTY,
                request,
                () -> unitUnderTest.findById(request).get());
    }

    @Test
    void findBattleLog() throws Exception {
        String playerTag = "playerTag";
        brawljars.api.intern.players.battlelog.BattleLogRequest.BattleLogRequestBuilder builder =
                brawljars.api.intern.players.battlelog.BattleLogRequest.builder(playerTag);
        brawljars.api.intern.players.battlelog.BattleLogRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/players/{playerTag}/battlelog".replace("{playerTag}", String.valueOf(playerTag)),
                EMPTY,
                "src/test/resources/player-findBattleLog.json",
                request);
        brawljars.api.intern.players.battlelog.BattleLogResponse expected =
                toJson(
                        brawljars.api.intern.players.battlelog.BattleLogResponse.class,
                        getExpected());

        run(expected, () -> unitUnderTest.findBattleLog(request).get());
    }

    @Test
    void findBattleLog_whenWithException() {
        String playerTag = "playerTag";
        brawljars.api.intern.players.battlelog.BattleLogRequest.BattleLogRequestBuilder builder =
                brawljars.api.intern.players.battlelog.BattleLogRequest.builder(playerTag);
        brawljars.api.intern.players.battlelog.BattleLogRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/players/{playerTag}/battlelog".replace("{playerTag}", String.valueOf(playerTag)),
                EMPTY,
                request,
                () -> unitUnderTest.findBattleLog(request).get());
    }
}
