package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetRankingsBrawlerResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetRankingsBrawlerRequest extends PageableRequest<GetRankingsBrawlerResponse> {

  private final String countryCode;
  private final String brawlerId;

  @Builder
  private GetRankingsBrawlerRequest(Callback<GetRankingsBrawlerResponse> callback, int limit, String after,
                                    String before, String countryCode, String brawlerId) {
    super(callback, limit, after, before);
    checkNotNull(countryCode);
    checkArgument(!countryCode.isEmpty());
    this.countryCode = countryCode;
    checkNotNull(brawlerId);
    checkArgument(!brawlerId.isEmpty());
    this.brawlerId = brawlerId;
  }

  public static GetRankingsBrawlerRequest.GetRankingsBrawlerRequestBuilder builder(
      String countryCode, String brawlerId) {
    return new GetRankingsBrawlerRequest.GetRankingsBrawlerRequestBuilder()
        .countryCode(countryCode)
        .brawlerId(brawlerId);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(countryCode);
    list.add(brawlerId);
    return list;
  }

}
