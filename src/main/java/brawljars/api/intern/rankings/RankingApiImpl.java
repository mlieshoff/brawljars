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

import brawljars.api.ApiContext;
import brawljars.api.BaseApi;
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

import java.util.concurrent.Future;

class RankingApiImpl extends BaseApi implements RankingApi {

    RankingApiImpl(ApiContext apiContext) {
        super(apiContext);
    }

    @Override
    public Future<PowerplayRankingsResponse> findPowerplayRankings(
            PowerplayRankingsRequest powerplayRankingsRequest) {
        return get(
                "/rankings/{countryCode}/powerplay/seasons/{seasonId}",
                powerplayRankingsRequest,
                PowerplayRankingsResponse.class);
    }

    public Future<PowerplayRankingsSeasonsResponse> findPowerplayRankingsSeasons(
            PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest) {
        return get(
                "/rankings/{countryCode}/powerplay/seasons",
                powerplayRankingsSeasonsRequest,
                PowerplayRankingsSeasonsResponse.class);
    }

    public Future<ClubRankingsResponse> findClubRankings(ClubRankingsRequest clubRankingsRequest) {
        return get(
                "/rankings/{countryCode}/clubs", clubRankingsRequest, ClubRankingsResponse.class);
    }

    public Future<BrawlerRankingsResponse> findBrawlerRankings(
            BrawlerRankingsRequest brawlerRankingsRequest) {
        return get(
                "/rankings/{countryCode}/brawlers/{brawlerId}",
                brawlerRankingsRequest,
                BrawlerRankingsResponse.class);
    }

    public Future<PlayerRankingsResponse> findPlayerRankings(
            PlayerRankingsRequest playerRankingsRequest) {
        return get(
                "/rankings/{countryCode}/players",
                playerRankingsRequest,
                PlayerRankingsResponse.class);
    }
}
