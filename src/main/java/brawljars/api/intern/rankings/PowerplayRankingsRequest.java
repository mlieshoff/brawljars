package brawljars.api.intern.rankings;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PowerplayRankingsRequest extends PaginationRequest<PowerplayRankingsResponse> {

  private final String countryCode;
  private final String seasonId;

  @Builder
  private PowerplayRankingsRequest(Callback<PowerplayRankingsResponse> callback, int limit, String after, String before, String countryCode, String seasonId) {
    super(callback, limit, after, before);
    this.countryCode = countryCode;
    this.seasonId = seasonId;
  }

  public static PowerplayRankingsRequestBuilder builder(String countryCode, String seasonId) {
    return new PowerplayRankingsRequestBuilder().countryCode(countryCode).seasonId(seasonId);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
    map.put("countryCode", countryCode);
    map.put("seasonId", seasonId);
    return map;
  }

}