package brawljars.api.intern.players.player;

import java.util.Map;
import brawljars.common.Request;
import lombok.Builder;

public class PlayerRequest extends Request<PlayerResponse> {

  private final String playerTag;

  @Builder
  private PlayerRequest(boolean storeRawResponse, String playerTag) {
    super(storeRawResponse);
    this.playerTag = playerTag;
  }

  public static PlayerRequestBuilder builder(String playerTag) {
    return new PlayerRequestBuilder()
      .playerTag(playerTag)
    ;
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
    map.put("playerTag", playerTag);
    return map;
  }

}