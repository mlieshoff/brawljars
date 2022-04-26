package brawljars;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.api.intern.brawlers.BrawlerApi;
import brawljars.api.intern.brawlers.BrawlerRequest;
import brawljars.api.intern.brawlers.BrawlerResponse;
import brawljars.api.intern.brawlers.BrawlersRequest;
import brawljars.api.intern.brawlers.BrawlersResponse;
import brawljars.api.intern.players.PlayerApi;
import brawljars.api.intern.players.battlelog.BattleLogRequest;
import brawljars.api.intern.players.battlelog.BattleLogResponse;
import brawljars.api.intern.players.player.PlayerRequest;
import brawljars.api.intern.players.player.PlayerResponse;
import brawljars.api.intern.rankings.PowerplayRankingsRequest;
import brawljars.api.intern.rankings.PowerplayRankingsResponse;
import brawljars.api.intern.rankings.RankingApi;
import brawljars.connector.StandardConnector;

public class EndToEnd {

  private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

  private BrawlJars brawlJars;

  private BrawlerApi brawlerApi;

  private RankingApi rankingApi;

  private PlayerApi playerApi;

  @BeforeEach
  void setUp() {
    brawlJars =
        new BrawlJars("https://bsproxy.royaleapi.dev/v1", System.getProperty("apiKey"), new StandardConnector());
    brawlerApi = brawlJars.getApi(BrawlerApi.class);
    rankingApi = brawlJars.getApi(RankingApi.class);
    playerApi = brawlJars.getApi(PlayerApi.class);
  }

  @Test
  void brawlers_findAll() {
    BrawlersResponse brawlersResponse = brawlerApi.findAll(BrawlersRequest.builder().build());
    String actual = GSON.toJson(brawlersResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void brawlers_findById() {
    BrawlerResponse brawlerResponse = brawlerApi.findById(BrawlerRequest.builder("16000000").build());
    String actual = GSON.toJson(brawlerResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findById() {
    PowerplayRankingsResponse
        powerplayRankingsResponse =
        rankingApi.findPowerplayRankings(PowerplayRankingsRequest.builder("DE", "81").build());
    String actual = GSON.toJson(powerplayRankingsResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void player_findById() {
    PlayerResponse
        playerResponse =
        playerApi.findById(PlayerRequest.builder("#28UP80RRY").build());
    String actual = GSON.toJson(playerResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void player_findBattleLog() {
    BattleLogResponse
        battleLogResponse =
        playerApi.findBattleLog(BattleLogRequest.builder("#28UP80RRY").build());
    String actual = GSON.toJson(battleLogResponse);
    String expected = brawlerApi.getLastResponse().getRaw().replace(",\"map\":null", "");

    assertEquals(expected, actual);
  }

}
