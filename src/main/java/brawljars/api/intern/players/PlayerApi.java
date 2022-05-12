package brawljars.api.intern.players;

import java.io.IOException;
import java.util.concurrent.Future;
import brawljars.api.Api;

public interface PlayerApi extends Api {

  Future<brawljars.api.intern.players.player.PlayerResponse> findById(brawljars.api.intern.players.player.PlayerRequest playerRequest);
  Future<brawljars.api.intern.players.battlelog.BattleLogResponse> findBattleLog(brawljars.api.intern.players.battlelog.BattleLogRequest battleLogRequest);

}
