package brawljars.generated;

import java.util.Map;
import brawljars.common.Request;
import lombok.Builder;
import lombok.Getter;

public class BrawlerRequest extends Request {

  private final String brawlerId;

  @Builder
  private BrawlerRequest(String brawlerId) {
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