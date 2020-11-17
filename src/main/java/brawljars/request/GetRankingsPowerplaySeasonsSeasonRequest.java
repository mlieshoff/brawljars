package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetRankingsPowerplaySeasonsSeasonResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetRankingsPowerplaySeasonsSeasonRequest
    extends PageableRequest<GetRankingsPowerplaySeasonsSeasonResponse> {

  private final String countryCode;
  private final String seasonId;

  @Builder
  private GetRankingsPowerplaySeasonsSeasonRequest(Callback<GetRankingsPowerplaySeasonsSeasonResponse> callback,
                                                   int limit, String after, String before, String countryCode,
                                                   String seasonId) {
    super(callback, limit, after, before);
    checkNotNull(countryCode);
    checkArgument(!countryCode.isEmpty());
    this.countryCode = countryCode;
    checkNotNull(seasonId);
    checkArgument(!seasonId.isEmpty());
    this.seasonId = seasonId;
  }

  public static GetRankingsPowerplaySeasonsSeasonRequest.GetRankingsPowerplaySeasonsSeasonRequestBuilder builder(
      String countryCode, String seasonId) {
    return new GetRankingsPowerplaySeasonsSeasonRequestBuilder()
        .countryCode(countryCode)
        .seasonId(seasonId);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(countryCode);
    list.add(seasonId);
    return list;
  }

}
