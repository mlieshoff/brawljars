package brawljars.api.intern.brawlers;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;

public class BrawlersRequest extends PaginationRequest<BrawlersResponse> {


  @Builder
  private BrawlersRequest(Callback<BrawlersResponse> callback, int limit, String after, String before, boolean storeRawResponse) {
    super(callback, limit, after, before, storeRawResponse);
  }

  public static BrawlersRequestBuilder builder() {
    return new BrawlersRequestBuilder();
  }


}