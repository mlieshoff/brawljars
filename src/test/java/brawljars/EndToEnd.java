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

import static org.junit.jupiter.api.Assertions.assertEquals;

import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import brawljars.api.connector.FilesystemCachedConnector;
import brawljars.api.intern.brawlers.BrawlerApi;
import brawljars.api.intern.brawlers.BrawlerRequest;
import brawljars.api.intern.brawlers.BrawlerResponse;
import brawljars.api.intern.brawlers.BrawlersRequest;
import brawljars.api.intern.brawlers.BrawlersResponse;
import brawljars.api.intern.clubs.ClubApi;
import brawljars.api.intern.clubs.info.ClubRequest;
import brawljars.api.intern.clubs.info.ClubResponse;
import brawljars.api.intern.clubs.member.ClubMembersRequest;
import brawljars.api.intern.clubs.member.ClubMembersResponse;
import brawljars.api.intern.events.EventApi;
import brawljars.api.intern.events.EventRotationRequest;
import brawljars.api.intern.events.EventRotationResponse;
import brawljars.api.intern.players.PlayerApi;
import brawljars.api.intern.players.battlelog.BattleLogRequest;
import brawljars.api.intern.players.battlelog.BattleLogResponse;
import brawljars.api.intern.players.info.PlayerRequest;
import brawljars.api.intern.players.info.PlayerResponse;
import brawljars.api.intern.rankings.RankingApi;
import brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest;
import brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse;
import brawljars.api.intern.rankings.club.ClubRankingsRequest;
import brawljars.api.intern.rankings.club.ClubRankingsResponse;
import brawljars.api.intern.rankings.player.PlayerRankingsRequest;
import brawljars.api.intern.rankings.player.PlayerRankingsResponse;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonPatch;
import javax.json.JsonValue;

public class EndToEnd {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private static final long SEASON_ID = 81L;
    private static final long BRAWLER_ID = 16000054L;

    private static final String PLAYER_TAG = "#28UP80RRY";
    private static final String CLUB_TAG = "#JUURPCV9";

    private BrawlerApi brawlerApi;

    private RankingApi rankingApi;

    private PlayerApi playerApi;

    private ClubApi clubApi;

    private EventApi eventApi;

    @BeforeEach
    void setUp() {
        BrawlJars brawlJars =
                new BrawlJars(
                        "https://bsproxy.royaleapi.dev/v1",
                        System.getProperty("apiKey", System.getenv("API_KEY")),
                        new FilesystemCachedConnector());
        brawlerApi = brawlJars.getApi(BrawlerApi.class);
        rankingApi = brawlJars.getApi(RankingApi.class);
        playerApi = brawlJars.getApi(PlayerApi.class);
        clubApi = brawlJars.getApi(ClubApi.class);
        eventApi = brawlJars.getApi(EventApi.class);
    }

    private static void assertDiff(String expected, String actual) {
        JsonValue source = Json.createReader(new StringReader(actual)).readValue();
        JsonValue target = Json.createReader(new StringReader(expected)).readValue();
        JsonPatch diff;
        try {
            diff = Json.createDiff(source.asJsonObject(), target.asJsonObject());
        } catch (ClassCastException e) {
            diff = Json.createDiff(source.asJsonArray(), target.asJsonArray());
        }
        StringBuilder diffOutput = new StringBuilder();
        diff.toJsonArray()
                .forEach(
                        entry ->
                                diffOutput
                                        .append(entry)
                                        .append(System.getProperty("line.separator")));
        assertEquals(EMPTY, diffOutput.toString());
    }

    @Test
    void player_findById() throws Exception {
        PlayerResponse playerResponse =
                playerApi
                        .findById(PlayerRequest.builder(PLAYER_TAG).storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(playerResponse);
        String expected = playerResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void player_findBattleLog() throws Exception {
        BattleLogResponse battleLogResponse =
                playerApi
                        .findBattleLog(
                                BattleLogRequest.builder(PLAYER_TAG).storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(battleLogResponse);
        String expected = deleteNullValue(battleLogResponse.getRawResponse().getRaw(), "map");

        assertDiff(expected, actual);
    }

    private static String deleteNullValue(String s, String key) {
        return s.replace(",\"" + key + "\":null", "");
    }

    @Test
    void club_findClub() throws Exception {
        ClubResponse clubResponse =
                clubApi.findClub(ClubRequest.builder(CLUB_TAG).storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(clubResponse);
        String expected = clubResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void club_findClubMembers() throws Exception {
        ClubMembersResponse clubMembersResponse =
                clubApi.findClubMembers(
                                ClubMembersRequest.builder(CLUB_TAG).storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(clubMembersResponse);
        String expected = clubMembersResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void rankings_findPowerplayRankings() throws Exception {
        PowerplayRankingsResponse powerplayRankingsResponse =
                rankingApi
                        .findPowerplayRankings(
                                PowerplayRankingsRequest.builder("DE", SEASON_ID)
                                        .storeRawResponse(true)
                                        .build())
                        .get();
        String actual = GSON.toJson(powerplayRankingsResponse);
        String expected = powerplayRankingsResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void rankings_findPowerplayRankingsSeasons() throws Exception {
        PowerplayRankingsSeasonsResponse powerplayRankingsSeasonsResponse =
                rankingApi
                        .findPowerplayRankingsSeasons(
                                PowerplayRankingsSeasonsRequest.builder("DE")
                                        .storeRawResponse(true)
                                        .build())
                        .get();
        String actual = GSON.toJson(powerplayRankingsSeasonsResponse);
        String expected = powerplayRankingsSeasonsResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void rankings_findClubRankings() throws Exception {
        ClubRankingsResponse clubRankingsResponse =
                rankingApi
                        .findClubRankings(
                                ClubRankingsRequest.builder("DE").storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(clubRankingsResponse);
        String expected = clubRankingsResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void rankings_findBrawlerRankings() throws Exception {
        BrawlerRankingsResponse brawlerRankingsResponse =
                rankingApi
                        .findBrawlerRankings(
                                BrawlerRankingsRequest.builder("DE", BRAWLER_ID)
                                        .storeRawResponse(true)
                                        .build())
                        .get();
        String actual = GSON.toJson(brawlerRankingsResponse);
        String expected = brawlerRankingsResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void rankings_findPlayerRankings() throws Exception {
        PlayerRankingsResponse playerRankingsResponse =
                rankingApi
                        .findPlayerRankings(
                                PlayerRankingsRequest.builder("DE").storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(playerRankingsResponse);
        String expected = playerRankingsResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void brawlers_findAll() throws Exception {
        BrawlersResponse brawlersResponse =
                brawlerApi.findAll(BrawlersRequest.builder().storeRawResponse(true).build()).get();
        String actual = GSON.toJson(brawlersResponse);
        String expected = brawlersResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void brawlers_findById() throws Exception {
        BrawlerResponse brawlerResponse =
                brawlerApi
                        .findById(BrawlerRequest.builder(BRAWLER_ID).storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(brawlerResponse);
        String expected = brawlerResponse.getRawResponse().getRaw();

        assertDiff(expected, actual);
    }

    @Test
    void events_findEventRotation() throws Exception {
        EventRotationResponse eventRotationResponse =
                eventApi.findEventRotation(
                                EventRotationRequest.builder().storeRawResponse(true).build())
                        .get();
        String actual = GSON.toJson(eventRotationResponse);
        String expected = deleteNullValue(eventRotationResponse.getRawResponse().getRaw(), "map");

        assertDiff(expected, actual);
    }
}
