package brawljars.api.intern.players;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;
import brawljars.common.BlockingCallback;

public class PlayerApiImplIntegrationTest extends IntegrationTestBase {

  private PlayerApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(PlayerApi.class);
  }

  @Test
  void findById() throws Exception {
    run_findById(false);
  }

  void run_findById(boolean withCallback) throws Exception {
    String playerTag = "playerTag";
    brawljars.api.intern.players.player.PlayerRequest.PlayerRequestBuilder builder = brawljars.api.intern.players.player.PlayerRequest.builder(playerTag);
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.players.player.PlayerRequest request = builder.build();
    prepare("/players/{playerTag}".replace("{playerTag}", String.valueOf(playerTag)), "src/test/resources/player-findById.json", request);

    brawljars.api.intern.players.player.PlayerResponse actual;
    if (withCallback) {
      unitUnderTest.findById(request);
      actual = ((BlockingCallback<brawljars.api.intern.players.player.PlayerResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findById(request);
    }
    brawljars.api.intern.players.player.PlayerResponse expected = toJson(brawljars.api.intern.players.player.PlayerResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findById_withCallback() throws Exception {
    run_findById(true);
  }

  @Test
  void findBattleLog() throws Exception {
    run_findBattleLog(false);
  }

  void run_findBattleLog(boolean withCallback) throws Exception {
    String playerTag = "playerTag";
    brawljars.api.intern.players.battlelog.BattleLogRequest.BattleLogRequestBuilder builder = brawljars.api.intern.players.battlelog.BattleLogRequest.builder(playerTag)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.players.battlelog.BattleLogRequest request = builder.build();
    prepare("/players/{playerTag}/battlelog".replace("{playerTag}", String.valueOf(playerTag)), "src/test/resources/player-findBattleLog.json", request);

    brawljars.api.intern.players.battlelog.BattleLogResponse actual;
    if (withCallback) {
      unitUnderTest.findBattleLog(request);
      actual = ((BlockingCallback<brawljars.api.intern.players.battlelog.BattleLogResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findBattleLog(request);
    }
    brawljars.api.intern.players.battlelog.BattleLogResponse expected = toJson(brawljars.api.intern.players.battlelog.BattleLogResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findBattleLog_withCallback() throws Exception {
    run_findBattleLog(true);
  }

}