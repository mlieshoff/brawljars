package brawljars.api.intern.players;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface PlayerApi extends Api {

  brawljars.api.intern.players.player.PlayerResponse findById(brawljars.api.intern.players.player.PlayerRequest playerRequest) throws ApiException;
  brawljars.api.intern.players.battlelog.BattleLogResponse findBattleLog(brawljars.api.intern.players.battlelog.BattleLogRequest battleLogRequest) throws ApiException;

}
