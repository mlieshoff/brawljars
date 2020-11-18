package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetBrawlerResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetBrawlerRequest extends Request<GetBrawlerResponse> {

  private final String brawlerId;

  @Builder
  private GetBrawlerRequest(Callback<GetBrawlerResponse> callback, String brawlerId) {
    super(callback);
    checkNotNull(brawlerId);
    checkArgument(!brawlerId.isEmpty());
    this.brawlerId = brawlerId;
  }

  public static GetBrawlerRequest.GetBrawlerRequestBuilder builder(String brawlerId) {
    return new GetBrawlerRequest.GetBrawlerRequestBuilder().brawlerId(brawlerId);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(brawlerId);
    return list;
  }

}
