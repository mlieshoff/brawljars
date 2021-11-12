package brawljars.api.intern.rankings;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class RankingsApiImpl extends BaseApi implements RankingsApi {

  public RankingsApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public PowerplayRankingsResponse findPowerplayRankings(PowerplayRankingsRequest powerplayRankingsRequest) throws ApiException {
    return get("/rankings/{countryCode}/powerplay/seasons/{seasonId}", powerplayRankingsRequest, PowerplayRankingsResponse.class);
  }

}