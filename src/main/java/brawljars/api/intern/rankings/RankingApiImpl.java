package brawljars.api.intern.rankings;

import java.util.concurrent.Future;
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

class RankingApiImpl extends BaseApi implements RankingApi {

  RankingApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<PowerplayRankingsResponse> findPowerplayRankings(PowerplayRankingsRequest powerplayRankingsRequest) {
    return get("/rankings/{countryCode}/powerplay/seasons/{seasonId}", powerplayRankingsRequest, PowerplayRankingsResponse.class);
  }

  public Future<PowerplayRankingsSeasonsResponse> findPowerplayRankingsSeasons(PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest) {
    return get("/rankings/{countryCode}/powerplay/seasons", powerplayRankingsSeasonsRequest, PowerplayRankingsSeasonsResponse.class);
  }

  public Future<ClubRankingsResponse> findClubRankings(ClubRankingsRequest clubRankingsRequest) {
    return get("/rankings/{countryCode}/clubs", clubRankingsRequest, ClubRankingsResponse.class);
  }

  public Future<BrawlerRankingsResponse> findBrawlerRankings(BrawlerRankingsRequest brawlerRankingsRequest) {
    return get("/rankings/{countryCode}/brawlers/{brawlerId}", brawlerRankingsRequest, BrawlerRankingsResponse.class);
  }

  public Future<PlayerRankingsResponse> findPlayerRankings(PlayerRankingsRequest playerRankingsRequest) {
    return get("/rankings/{countryCode}/players", playerRankingsRequest, PlayerRankingsResponse.class);
  }

}