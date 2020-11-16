package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetRankingsPowerplaySeasonsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetRankingsPowerplaySeasonsRequest extends PageableRequest<GetRankingsPowerplaySeasonsResponse> {

  private final String countryCode;

  @Builder
  private GetRankingsPowerplaySeasonsRequest(Callback<GetRankingsPowerplaySeasonsResponse> callback, int limit,
                                             String after, String before, String countryCode) {
    super(callback, limit, after, before);
    checkNotNull(countryCode);
    checkArgument(!countryCode.isEmpty());
    this.countryCode = countryCode;
  }

  public static GetRankingsPowerplaySeasonsRequest.GetRankingsPowerplaySeasonsRequestBuilder builder(
      String countryCode) {
    return new GetRankingsPowerplaySeasonsRequest.GetRankingsPowerplaySeasonsRequestBuilder().countryCode(countryCode);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(countryCode);
    return list;
  }

}
