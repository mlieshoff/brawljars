package brawljars.api.intern.brawlers;

import brawljars.common.PaginationRequest;
import lombok.Builder;

public class BrawlersRequest extends PaginationRequest {


  @Builder
  private BrawlersRequest(int limit, String after, String before, boolean storeRawResponse) {
    super(limit, after, before, storeRawResponse);
  }

  public static BrawlersRequestBuilder builder() {
    return new BrawlersRequestBuilder();
  }


}