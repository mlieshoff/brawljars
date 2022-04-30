package brawljars.api.intern.rankings.powerplay;

import java.util.Map;
import brawljars.common.PaginationRequest;
import lombok.Builder;

public class PowerplayRankingsRequest extends PaginationRequest<PowerplayRankingsResponse> {

  private final String countryCode;
  private final long seasonId;

  @Builder
  private PowerplayRankingsRequest(int limit, String after, String before, boolean storeRawResponse, String countryCode, long seasonId) {
    super(limit, after, before, storeRawResponse);
    this.countryCode = countryCode;
    this.seasonId = seasonId;
  }

  public static PowerplayRankingsRequestBuilder builder(String countryCode, long seasonId) {
    return new PowerplayRankingsRequestBuilder().countryCode(countryCode).seasonId(seasonId);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("countryCode", countryCode);
      map.put("seasonId", String.valueOf(seasonId));
    return map;
  }

}