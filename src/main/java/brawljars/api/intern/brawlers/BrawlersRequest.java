package brawljars.api.intern.brawlers;

import brawljars.common.Callback;
import brawljars.common.PaginationRequest;
import lombok.Builder;

public class BrawlersRequest extends PaginationRequest<BrawlersResponse> {


  @Builder
  private BrawlersRequest(Callback<BrawlersResponse> callback, int limit, String after, String before) {
    super(callback, limit, after, before);
  }

  public static BrawlersRequestBuilder builder() {
    return new BrawlersRequestBuilder();
  }


}