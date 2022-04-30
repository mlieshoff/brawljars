package brawljars.api.intern.club;

import java.util.Map;
import brawljars.common.Request;
import brawljars.common.Callback;
import lombok.Builder;

public class ClubRequest extends Request<ClubResponse> {

  private final String clubTag;

  @Builder
  private ClubRequest(Callback<ClubResponse> callback, String clubTag) {
    super(callback);
    this.clubTag = clubTag;
  }

  public static ClubRequestBuilder builder(String clubTag) {
    return new ClubRequestBuilder()
      .clubTag(clubTag)
    ;
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
    map.put("clubTag", clubTag);
    return map;
  }

}