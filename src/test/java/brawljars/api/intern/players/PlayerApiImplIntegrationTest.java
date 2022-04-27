package brawljars.api.intern.players;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class PlayerApiImplIntegrationTest extends IntegrationTestBase {

  private PlayerApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(PlayerApi.class);
  }

  @Test
  void findById() throws Exception {
    String playerTag = "playerTag";
    brawljars.api.intern.players.player.PlayerRequest playerRequest = brawljars.api.intern.players.player.PlayerRequest.builder(playerTag)
      .build();
    prepare("/players/{playerTag}".replace("{playerTag}", String.valueOf(playerTag)), "src/test/resources/player-findById.json", playerRequest);

    brawljars.api.intern.players.player.PlayerResponse actual = unitUnderTest.findById(playerRequest);
    brawljars.api.intern.players.player.PlayerResponse expected = toJson(brawljars.api.intern.players.player.PlayerResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findBattleLog() throws Exception {
    String playerTag = "playerTag";
    brawljars.api.intern.players.battlelog.BattleLogRequest battleLogRequest = brawljars.api.intern.players.battlelog.BattleLogRequest.builder(playerTag)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/players/{playerTag}/battlelog".replace("{playerTag}", String.valueOf(playerTag)), "src/test/resources/player-findBattleLog.json", battleLogRequest);

    brawljars.api.intern.players.battlelog.BattleLogResponse actual = unitUnderTest.findBattleLog(battleLogRequest);
    brawljars.api.intern.players.battlelog.BattleLogResponse expected = toJson(brawljars.api.intern.players.battlelog.BattleLogResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}