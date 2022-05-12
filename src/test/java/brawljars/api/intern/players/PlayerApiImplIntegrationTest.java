package brawljars.api.intern.players;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

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
    brawljars.api.intern.players.player.PlayerRequest.PlayerRequestBuilder builder = brawljars.api.intern.players.player.PlayerRequest.builder(playerTag);
    brawljars.api.intern.players.player.PlayerRequest request = builder
      .storeRawResponse(true)
      .build();
    prepare("/players/{playerTag}".replace("{playerTag}", String.valueOf(playerTag)), "src/test/resources/player-findById.json", request);
    brawljars.api.intern.players.player.PlayerResponse expected = toJson(brawljars.api.intern.players.player.PlayerResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findById(request).get());
  }

  @Test
  void findById_whenWithException() {
    String playerTag = "playerTag";
    brawljars.api.intern.players.player.PlayerRequest.PlayerRequestBuilder builder = brawljars.api.intern.players.player.PlayerRequest.builder(playerTag);
    brawljars.api.intern.players.player.PlayerRequest request = builder
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/players/{playerTag}".replace("{playerTag}", String.valueOf(playerTag)), request, () -> unitUnderTest.findById(request).get());
  }

  @Test
  void findBattleLog() throws Exception {
    String playerTag = "playerTag";
    brawljars.api.intern.players.battlelog.BattleLogRequest.BattleLogRequestBuilder builder = brawljars.api.intern.players.battlelog.BattleLogRequest.builder(playerTag);
    brawljars.api.intern.players.battlelog.BattleLogRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/players/{playerTag}/battlelog".replace("{playerTag}", String.valueOf(playerTag)), "src/test/resources/player-findBattleLog.json", request);
    brawljars.api.intern.players.battlelog.BattleLogResponse expected = toJson(brawljars.api.intern.players.battlelog.BattleLogResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findBattleLog(request).get());
  }

  @Test
  void findBattleLog_whenWithException() {
    String playerTag = "playerTag";
    brawljars.api.intern.players.battlelog.BattleLogRequest.BattleLogRequestBuilder builder = brawljars.api.intern.players.battlelog.BattleLogRequest.builder(playerTag);
    brawljars.api.intern.players.battlelog.BattleLogRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/players/{playerTag}/battlelog".replace("{playerTag}", String.valueOf(playerTag)), request, () -> unitUnderTest.findBattleLog(request).get());
  }

}