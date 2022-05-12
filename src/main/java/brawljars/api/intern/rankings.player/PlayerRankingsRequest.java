package brawljars.api.intern.rankings.player;

import java.util.Map;
import brawljars.common.PaginationRequest;
import lombok.Builder;

public class PlayerRankingsRequest extends PaginationRequest {

  private final String countryCode;

  @Builder
  private PlayerRankingsRequest(int limit, String after, String before, boolean storeRawResponse, String countryCode) {
    super(limit, after, before, storeRawResponse);
    this.countryCode = countryCode;
  }

  public static PlayerRankingsRequestBuilder builder(String countryCode) {
    return new PlayerRankingsRequestBuilder().countryCode(countryCode);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("countryCode", countryCode);
    return map;
  }

}