package brawljars.api.intern.rankings;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface RankingApi extends Api {

  brawljars.api.intern.rankings.PowerplayRankingsResponse findPowerplayRankings(brawljars.api.intern.rankings.PowerplayRankingsRequest powerplayRankingsRequest) throws ApiException;

}
