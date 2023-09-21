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
package brawljars.api.intern.rankings;

import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import brawljars.IntegrationTestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RankingApiImplIntegrationTest extends IntegrationTestBase {

    private RankingApi unitUnderTest;

    @BeforeEach
    void setUp() {
        unitUnderTest = getBrawlJars().getApi(RankingApi.class);
    }

    @Test
    void findPowerplayRankings() throws Exception {
        String countryCode = "countryCode";
        long seasonId = 4711L;
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest
                        .PowerplayRankingsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.builder(
                                countryCode, seasonId);
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/rankings/{countryCode}/powerplay/seasons/{seasonId}"
                        .replace("{countryCode}", countryCode)
                        .replace("{seasonId}", String.valueOf(seasonId)),
                EMPTY,
                "src/test/resources/ranking-findPowerplayRankings.json",
                request);
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse expected =
                toJson(
                        brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse.class,
                        getExpected());

        run(expected, () -> unitUnderTest.findPowerplayRankings(request).get());
    }

    @Test
    void findPowerplayRankings_whenWithException() {
        String countryCode = "countryCode";
        long seasonId = 4711L;
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest
                        .PowerplayRankingsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.builder(
                                countryCode, seasonId);
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/rankings/{countryCode}/powerplay/seasons/{seasonId}"
                        .replace("{countryCode}", countryCode)
                        .replace("{seasonId}", String.valueOf(seasonId)),
                EMPTY,
                request,
                () -> unitUnderTest.findPowerplayRankings(request).get());
    }

    @Test
    void findPowerplayRankingsSeasons() throws Exception {
        String countryCode = "countryCode";
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest
                        .PowerplayRankingsSeasonsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest
                                .builder(countryCode);
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/rankings/{countryCode}/powerplay/seasons".replace("{countryCode}", countryCode),
                EMPTY,
                "src/test/resources/ranking-findPowerplayRankingsSeasons.json",
                request);
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse expected =
                toJson(
                        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse
                                .class,
                        getExpected());

        run(expected, () -> unitUnderTest.findPowerplayRankingsSeasons(request).get());
    }

    @Test
    void findPowerplayRankingsSeasons_whenWithException() {
        String countryCode = "countryCode";
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest
                        .PowerplayRankingsSeasonsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest
                                .builder(countryCode);
        brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/rankings/{countryCode}/powerplay/seasons".replace("{countryCode}", countryCode),
                EMPTY,
                request,
                () -> unitUnderTest.findPowerplayRankingsSeasons(request).get());
    }

    @Test
    void findClubRankings() throws Exception {
        String countryCode = "countryCode";
        brawljars.api.intern.rankings.club.ClubRankingsRequest.ClubRankingsRequestBuilder builder =
                brawljars.api.intern.rankings.club.ClubRankingsRequest.builder(countryCode);
        brawljars.api.intern.rankings.club.ClubRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/rankings/{countryCode}/clubs".replace("{countryCode}", countryCode),
                EMPTY,
                "src/test/resources/ranking-findClubRankings.json",
                request);
        brawljars.api.intern.rankings.club.ClubRankingsResponse expected =
                toJson(
                        brawljars.api.intern.rankings.club.ClubRankingsResponse.class,
                        getExpected());

        run(expected, () -> unitUnderTest.findClubRankings(request).get());
    }

    @Test
    void findClubRankings_whenWithException() {
        String countryCode = "countryCode";
        brawljars.api.intern.rankings.club.ClubRankingsRequest.ClubRankingsRequestBuilder builder =
                brawljars.api.intern.rankings.club.ClubRankingsRequest.builder(countryCode);
        brawljars.api.intern.rankings.club.ClubRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/rankings/{countryCode}/clubs".replace("{countryCode}", countryCode),
                EMPTY,
                request,
                () -> unitUnderTest.findClubRankings(request).get());
    }

    @Test
    void findBrawlerRankings() throws Exception {
        String countryCode = "countryCode";
        long brawlerId = 4711L;
        brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.BrawlerRankingsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.builder(
                                countryCode, brawlerId);
        brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/rankings/{countryCode}/brawlers/{brawlerId}"
                        .replace("{countryCode}", countryCode)
                        .replace("{brawlerId}", String.valueOf(brawlerId)),
                EMPTY,
                "src/test/resources/ranking-findBrawlerRankings.json",
                request);
        brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse expected =
                toJson(
                        brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse.class,
                        getExpected());

        run(expected, () -> unitUnderTest.findBrawlerRankings(request).get());
    }

    @Test
    void findBrawlerRankings_whenWithException() {
        String countryCode = "countryCode";
        long brawlerId = 4711L;
        brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.BrawlerRankingsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.builder(
                                countryCode, brawlerId);
        brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/rankings/{countryCode}/brawlers/{brawlerId}"
                        .replace("{countryCode}", countryCode)
                        .replace("{brawlerId}", String.valueOf(brawlerId)),
                EMPTY,
                request,
                () -> unitUnderTest.findBrawlerRankings(request).get());
    }

    @Test
    void findPlayerRankings() throws Exception {
        String countryCode = "countryCode";
        brawljars.api.intern.rankings.player.PlayerRankingsRequest.PlayerRankingsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.player.PlayerRankingsRequest.builder(
                                countryCode);
        brawljars.api.intern.rankings.player.PlayerRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/rankings/{countryCode}/players".replace("{countryCode}", countryCode),
                EMPTY,
                "src/test/resources/ranking-findPlayerRankings.json",
                request);
        brawljars.api.intern.rankings.player.PlayerRankingsResponse expected =
                toJson(
                        brawljars.api.intern.rankings.player.PlayerRankingsResponse.class,
                        getExpected());

        run(expected, () -> unitUnderTest.findPlayerRankings(request).get());
    }

    @Test
    void findPlayerRankings_whenWithException() {
        String countryCode = "countryCode";
        brawljars.api.intern.rankings.player.PlayerRankingsRequest.PlayerRankingsRequestBuilder
                builder =
                        brawljars.api.intern.rankings.player.PlayerRankingsRequest.builder(
                                countryCode);
        brawljars.api.intern.rankings.player.PlayerRankingsRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/rankings/{countryCode}/players".replace("{countryCode}", countryCode),
                EMPTY,
                request,
                () -> unitUnderTest.findPlayerRankings(request).get());
    }
}
