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

import java.util.concurrent.Future;
import brawljars.api.Api;
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

public interface RankingApi extends Api {

  Future<PowerplayRankingsResponse> findPowerplayRankings(PowerplayRankingsRequest powerplayRankingsRequest);

  Future<PowerplayRankingsSeasonsResponse> findPowerplayRankingsSeasons(
      PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest);

  Future<ClubRankingsResponse> findClubRankings(ClubRankingsRequest clubRankingsRequest);

  Future<BrawlerRankingsResponse> findBrawlerRankings(BrawlerRankingsRequest brawlerRankingsRequest);

  Future<PlayerRankingsResponse> findPlayerRankings(PlayerRankingsRequest playerRankingsRequest);

}
