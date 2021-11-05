package brawljars.v2.api.generated;

import brawljars.v2.api.common.PaginationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrawlersRequest extends PaginationRequest {

  @Builder
  private BrawlersRequest(int limit, String after, String before) {
    super(limit, after, before);
  }

  public static BrawlersRequestBuilder builder() {
    return new BrawlersRequestBuilder();
  }

}