package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetClubMembersResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetClubMembersRequest extends PageableRequest<GetClubMembersResponse> {

  private final String clubTag;

  @Builder
  private GetClubMembersRequest(Callback<GetClubMembersResponse> callback, int limit, String after, String before,
                                String clubTag) {
    super(callback, limit, after, before);
    checkNotNull(clubTag);
    checkArgument(!clubTag.isEmpty());
    this.clubTag = clubTag;
  }

  public static GetClubMembersRequest.GetClubMembersRequestBuilder builder(String playerTag) {
    return new GetClubMembersRequestBuilder().clubTag(playerTag);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(clubTag);
    return list;
  }

}
