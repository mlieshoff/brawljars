package brawljars.api.intern.brawlers;

import java.util.Map;
import brawljars.common.Request;
import lombok.Builder;

public class BrawlerRequest extends Request<BrawlerResponse> {

  private final long brawlerId;

  @Builder
  private BrawlerRequest(boolean storeRawResponse, long brawlerId) {
    super(storeRawResponse);
    this.brawlerId = brawlerId;
  }

  public static BrawlerRequestBuilder builder(long brawlerId) {
    return new BrawlerRequestBuilder()
      .brawlerId(brawlerId)
    ;
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
    map.put("brawlerId", String.valueOf(brawlerId));
    return map;
  }

}