package brawljars.api.intern.brawlers;

import java.util.Map;
import brawljars.common.Request;
import brawljars.common.Callback;
import lombok.Builder;

public class BrawlerRequest extends Request<BrawlerResponse> {

  private final String brawlerId;

  @Builder
  private BrawlerRequest(Callback<BrawlerResponse> callback, String brawlerId) {
    super(callback);
    this.brawlerId = brawlerId;
  }

  public static BrawlerRequestBuilder builder(String brawlerId) {
    return new BrawlerRequestBuilder()
      .brawlerId(brawlerId)
    ;
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
    map.put("brawlerId", brawlerId);
    return map;
  }

}