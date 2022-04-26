package brawljars.api.intern.players;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class PlayerApiImpl extends BaseApi implements PlayerApi {

  public PlayerApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public brawljars.api.intern.players.player.PlayerResponse findById(brawljars.api.intern.players.player.PlayerRequest playerRequest) throws ApiException {
    return get("/players/{playerTag}", playerRequest, brawljars.api.intern.players.player.PlayerResponse.class);
  }

  public brawljars.api.intern.players.battlelog.BattleLogResponse findBattleLog(brawljars.api.intern.players.battlelog.BattleLogRequest battleLogRequest) throws ApiException {
    return get("/players/{playerTag}/battlelog", battleLogRequest, brawljars.api.intern.players.battlelog.BattleLogResponse.class);
  }

}