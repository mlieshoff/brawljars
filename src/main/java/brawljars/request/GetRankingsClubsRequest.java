package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetRankingsClubsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetRankingsClubsRequest extends PageableRequest<GetRankingsClubsResponse> {

  private final String countryCode;

  @Builder
  private GetRankingsClubsRequest(Callback<GetRankingsClubsResponse> callback, int limit,
                                  String after, String before, String countryCode) {
    super(callback, limit, after, before);
    checkNotNull(countryCode);
    checkArgument(!countryCode.isEmpty());
    this.countryCode = countryCode;
  }

  public static GetRankingsClubsRequest.GetRankingsClubsRequestBuilder builder(
      String countryCode) {
    return new GetRankingsClubsRequest.GetRankingsClubsRequestBuilder().countryCode(countryCode);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(countryCode);
    return list;
  }

}
