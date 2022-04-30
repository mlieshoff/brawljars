package brawljars.api.intern.rankings.brawler;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;

public class BrawlerRankingsRequest extends PaginationRequest<BrawlerRankingsResponse> {

  private final String countryCode;
  private final long brawlerId;

  @Builder
  private BrawlerRankingsRequest(Callback<BrawlerRankingsResponse> callback, int limit, String after, String before, boolean storeRawResponse, String countryCode, long brawlerId) {
    super(callback, limit, after, before, storeRawResponse);
    this.countryCode = countryCode;
    this.brawlerId = brawlerId;
  }

  public static BrawlerRankingsRequestBuilder builder(String countryCode, long brawlerId) {
    return new BrawlerRankingsRequestBuilder().countryCode(countryCode).brawlerId(brawlerId);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("countryCode", countryCode);
      map.put("brawlerId", String.valueOf(brawlerId));
    return map;
  }

}