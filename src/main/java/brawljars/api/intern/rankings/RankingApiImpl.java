package brawljars.api.intern.rankings;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class RankingApiImpl extends BaseApi implements RankingApi {

  public RankingApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public brawljars.api.intern.rankings.PowerplayRankingsResponse findPowerplayRankings(brawljars.api.intern.rankings.PowerplayRankingsRequest powerplayRankingsRequest) throws ApiException {
    return get("/rankings/{countryCode}/powerplay/seasons/{seasonId}", powerplayRankingsRequest, brawljars.api.intern.rankings.PowerplayRankingsResponse.class);
  }

}