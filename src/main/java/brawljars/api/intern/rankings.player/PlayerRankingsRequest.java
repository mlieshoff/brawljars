package brawljars.api.intern.rankings.player;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;

public class PlayerRankingsRequest extends PaginationRequest<PlayerRankingsResponse> {

  private final String countryCode;

  @Builder
  private PlayerRankingsRequest(Callback<PlayerRankingsResponse> callback, int limit, String after, String before, String countryCode) {
    super(callback, limit, after, before);
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