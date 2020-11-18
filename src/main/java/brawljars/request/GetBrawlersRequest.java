package brawljars.request;

import brawljars.response.Callback;
import brawljars.response.GetBrawlersResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetBrawlersRequest extends PageableRequest<GetBrawlersResponse> {

  @Builder
  private GetBrawlersRequest(Callback<GetBrawlersResponse> callback, int limit, String after, String before) {
    super(callback, limit, after, before);
  }

  public static GetBrawlersRequest.GetBrawlersRequestBuilder builder() {
    return new GetBrawlersRequest.GetBrawlersRequestBuilder();
  }

}
