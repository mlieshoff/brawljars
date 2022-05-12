package brawljars.api.intern.club;

import java.util.Map;
import brawljars.common.PaginationRequest;
import lombok.Builder;

public class ClubMembersRequest extends PaginationRequest {

  private final String clubTag;

  @Builder
  private ClubMembersRequest(int limit, String after, String before, boolean storeRawResponse, String clubTag) {
    super(limit, after, before, storeRawResponse);
    this.clubTag = clubTag;
  }

  public static ClubMembersRequestBuilder builder(String clubTag) {
    return new ClubMembersRequestBuilder().clubTag(clubTag);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("clubTag", clubTag);
    return map;
  }

}