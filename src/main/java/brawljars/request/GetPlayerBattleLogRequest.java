package brawljars.request;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import brawljars.response.Callback;
import brawljars.response.GetPlayerBattleLogResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPlayerBattleLogRequest extends PageableRequest<GetPlayerBattleLogResponse> {

  private final String playerTag;

  @Builder
  private GetPlayerBattleLogRequest(Callback<GetPlayerBattleLogResponse> callback, int limit, String after,
                                    String before, String playerTag) {
    super(callback, limit, after, before);
    checkNotNull(playerTag);
    checkArgument(!playerTag.isEmpty());
    this.playerTag = playerTag;
  }

  public static GetPlayerBattleLogRequest.GetPlayerBattleLogRequestBuilder builder(String playerTag) {
    return new GetPlayerBattleLogRequest.GetPlayerBattleLogRequestBuilder().playerTag(playerTag);
  }

  @Override
  public List<String> getRestParameters() {
    List<String> list = super.getRestParameters();
    list.add(playerTag);
    return list;
  }

}
