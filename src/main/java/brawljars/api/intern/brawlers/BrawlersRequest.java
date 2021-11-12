package brawljars.api.intern.brawlers;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrawlersRequest extends PaginationRequest<BrawlersResponse> {


  @Builder
  private BrawlersRequest(Callback<BrawlersResponse> callback, int limit, String after, String before) {
    super(callback, limit, after, before);
  }

  public static BrawlersRequestBuilder builder() {
    return new BrawlersRequestBuilder();
  }


}