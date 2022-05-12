package brawljars.api.intern.players;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;
import brawljars.api.intern.players.battlelog.BattleLogRequest;
import brawljars.api.intern.players.battlelog.BattleLogResponse;
import brawljars.api.intern.players.player.PlayerRequest;
import brawljars.api.intern.players.player.PlayerResponse;

class PlayerApiImpl extends BaseApi implements PlayerApi {

  PlayerApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<PlayerResponse> findById(PlayerRequest playerRequest) {
    return get("/players/{playerTag}", playerRequest, PlayerResponse.class);
  }

  public Future<BattleLogResponse> findBattleLog(BattleLogRequest battleLogRequest) {
    return get("/players/{playerTag}/battlelog", battleLogRequest, BattleLogResponse.class);
  }

}