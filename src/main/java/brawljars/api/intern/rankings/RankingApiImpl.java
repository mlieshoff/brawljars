package brawljars.api.intern.rankings;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class RankingApiImpl extends BaseApi implements RankingApi {

  public RankingApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse findPowerplayRankings(brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest powerplayRankingsRequest) throws ApiException {
    return get("/rankings/{countryCode}/powerplay/seasons/{seasonId}", powerplayRankingsRequest, brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse.class);
  }

  public brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse findPowerplayRankingsSeasons(brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest) throws ApiException {
    return get("/rankings/{countryCode}/powerplay/seasons", powerplayRankingsSeasonsRequest, brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse.class);
  }

  public brawljars.api.intern.rankings.club.ClubRankingsResponse findClubRankings(brawljars.api.intern.rankings.club.ClubRankingsRequest clubRankingsRequest) throws ApiException {
    return get("/rankings/{countryCode}/clubs", clubRankingsRequest, brawljars.api.intern.rankings.club.ClubRankingsResponse.class);
  }

  public brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse findBrawlerRankings(brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest brawlerRankingsRequest) throws ApiException {
    return get("/rankings/{countryCode}/brawlers/{brawlerId}", brawlerRankingsRequest, brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse.class);
  }

  public brawljars.api.intern.rankings.player.PlayerRankingsResponse findPlayerRankings(brawljars.api.intern.rankings.player.PlayerRankingsRequest playerRankingsRequest) throws ApiException {
    return get("/rankings/{countryCode}/players", playerRankingsRequest, brawljars.api.intern.rankings.player.PlayerRankingsResponse.class);
  }

}