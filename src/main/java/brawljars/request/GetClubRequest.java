package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetClubResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetClubRequest extends Request<GetClubResponse> {

  private final String clubTag;

  @Builder
  private GetClubRequest(Callback<GetClubResponse> callback, String clubTag) {
    super(callback);
    checkNotNull(clubTag);
    checkArgument(!clubTag.isEmpty());
    this.clubTag = clubTag;
  }

  public static GetClubRequest.GetClubRequestBuilder builder(String clubTag) {
    return new GetClubRequest.GetClubRequestBuilder().clubTag(clubTag);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(clubTag);
    return list;
  }

}
