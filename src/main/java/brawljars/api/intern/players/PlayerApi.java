package brawljars.api.intern.players;

import java.util.concurrent.Future;
import brawljars.api.Api;
import brawljars.api.intern.players.battlelog.BattleLogRequest;
import brawljars.api.intern.players.battlelog.BattleLogResponse;
import brawljars.api.intern.players.player.PlayerRequest;
import brawljars.api.intern.players.player.PlayerResponse;

public interface PlayerApi extends Api {

  Future<PlayerResponse> findById(PlayerRequest playerRequest);
  Future<BattleLogResponse> findBattleLog(BattleLogRequest battleLogRequest);

}
