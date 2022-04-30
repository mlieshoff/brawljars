package brawljars.api.intern.rankings.club;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;

public class ClubRankingsRequest extends PaginationRequest<ClubRankingsResponse> {

  private final String countryCode;

  @Builder
  private ClubRankingsRequest(Callback<ClubRankingsResponse> callback, int limit, String after, String before, boolean storeRawResponse, String countryCode) {
    super(callback, limit, after, before, storeRawResponse);
    this.countryCode = countryCode;
  }

  public static ClubRankingsRequestBuilder builder(String countryCode) {
    return new ClubRankingsRequestBuilder().countryCode(countryCode);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("countryCode", countryCode);
    return map;
  }

}