package brawljars.api.intern.players;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;

class PlayerApiImpl extends BaseApi implements PlayerApi {

  public PlayerApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<brawljars.api.intern.players.player.PlayerResponse> findById(brawljars.api.intern.players.player.PlayerRequest playerRequest) {
    return get("/players/{playerTag}", playerRequest, brawljars.api.intern.players.player.PlayerResponse.class);
  }

  public Future<brawljars.api.intern.players.battlelog.BattleLogResponse> findBattleLog(brawljars.api.intern.players.battlelog.BattleLogRequest battleLogRequest) {
    return get("/players/{playerTag}/battlelog", battleLogRequest, brawljars.api.intern.players.battlelog.BattleLogResponse.class);
  }

}