package brawljars.generated;

import brawljars.common.PaginationRequest;
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