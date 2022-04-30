package brawljars.api.intern.rankings.powerplay;

import java.util.Map;
import brawljars.common.PaginationRequest;
import lombok.Builder;

public class PowerplayRankingsSeasonsRequest extends PaginationRequest<PowerplayRankingsSeasonsResponse> {

  private final String countryCode;

  @Builder
  private PowerplayRankingsSeasonsRequest(int limit, String after, String before, boolean storeRawResponse, String countryCode) {
    super(limit, after, before, storeRawResponse);
    this.countryCode = countryCode;
  }

  public static PowerplayRankingsSeasonsRequestBuilder builder(String countryCode) {
    return new PowerplayRankingsSeasonsRequestBuilder().countryCode(countryCode);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("countryCode", countryCode);
    return map;
  }

}