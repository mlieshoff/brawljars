package brawljars.api.intern.players.battlelog;

import java.util.Map;
import brawljars.common.PaginationRequest;
import brawljars.common.Callback;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BattleLogRequest extends PaginationRequest<BattleLogResponse> {

  private final String playerTag;

  @Builder
  private BattleLogRequest(Callback<BattleLogResponse> callback, int limit, String after, String before, String playerTag) {
    super(callback, limit, after, before);
    this.playerTag = playerTag;
  }

  public static BattleLogRequestBuilder builder(String playerTag) {
    return new BattleLogRequestBuilder().playerTag(playerTag);
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
      map.put("playerTag", playerTag);
    return map;
  }

}