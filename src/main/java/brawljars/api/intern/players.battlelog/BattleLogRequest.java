package brawljars.api.intern.players.battlelog;

import java.util.Map;
import brawljars.common.PaginationRequest;
import lombok.Builder;

public class BattleLogRequest extends PaginationRequest {

  private final String playerTag;

  @Builder
  private BattleLogRequest(int limit, String after, String before, boolean storeRawResponse, String playerTag) {
    super(limit, after, before, storeRawResponse);
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