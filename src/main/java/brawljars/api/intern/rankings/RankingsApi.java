package brawljars.api.intern.rankings;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface RankingsApi extends Api {

  PowerplayRankingsResponse findPowerplayRankings(PowerplayRankingsRequest powerplayRankingsRequest) throws ApiException;

}
