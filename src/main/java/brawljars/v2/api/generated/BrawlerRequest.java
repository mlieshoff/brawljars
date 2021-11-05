package brawljars.v2.api.generated;

import java.util.Map;
import brawljars.v2.api.common.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
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