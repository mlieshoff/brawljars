package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetPlayerResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPlayerRequest extends Request<GetPlayerResponse> {

  private final String playerTag;

  @Builder
  private GetPlayerRequest(Callback<GetPlayerResponse> callback, String playerTag) {
    super(callback);
    checkNotNull(playerTag);
    checkArgument(!playerTag.isEmpty());
    this.playerTag = playerTag;
  }

  public static GetPlayerRequestBuilder builder(String playerTag) {
    return new GetPlayerRequestBuilder().playerTag(playerTag);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(playerTag);
    return list;
  }

}
