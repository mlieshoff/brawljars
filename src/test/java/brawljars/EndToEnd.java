package brawljars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonPatch;
import javax.json.JsonValue;
import brawljars.api.intern.brawlers.BrawlerApi;
import brawljars.api.intern.brawlers.BrawlerRequest;
import brawljars.api.intern.brawlers.BrawlerResponse;
import brawljars.api.intern.brawlers.BrawlersRequest;
import brawljars.api.intern.brawlers.BrawlersResponse;
import brawljars.api.intern.club.ClubApi;
import brawljars.api.intern.club.ClubMembersRequest;
import brawljars.api.intern.club.ClubMembersResponse;
import brawljars.api.intern.club.ClubRequest;
import brawljars.api.intern.club.ClubResponse;
import brawljars.api.intern.event.EventApi;
import brawljars.api.intern.event.EventRotationRequest;
import brawljars.api.intern.event.EventRotationResponse;
import brawljars.api.intern.players.PlayerApi;
import brawljars.api.intern.players.battlelog.BattleLogRequest;
import brawljars.api.intern.players.battlelog.BattleLogResponse;
import brawljars.api.intern.players.player.PlayerRequest;
import brawljars.api.intern.players.player.PlayerResponse;
import brawljars.api.intern.rankings.RankingApi;
import brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest;
import brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse;
import brawljars.api.intern.rankings.club.ClubRankingsRequest;
import brawljars.api.intern.rankings.club.ClubRankingsResponse;
import brawljars.api.intern.rankings.player.PlayerRankingsRequest;
import brawljars.api.intern.rankings.player.PlayerRankingsResponse;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse;
import brawljars.connector.StandardConnector;
import wiremock.org.apache.commons.lang3.StringUtils;

public class EndToEnd {

  private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

  private BrawlJars brawlJars;

  private BrawlerApi brawlerApi;

  private RankingApi rankingApi;

  private PlayerApi playerApi;

  private ClubApi clubApi;

  private EventApi eventApi;

  @BeforeEach
  void setUp() {
    brawlJars =
        new BrawlJars("https://bsproxy.royaleapi.dev/v1", System.getProperty("apiKey"), new StandardConnector());
    brawlerApi = brawlJars.getApi(BrawlerApi.class);
    rankingApi = brawlJars.getApi(RankingApi.class);
    playerApi = brawlJars.getApi(PlayerApi.class);
    clubApi = brawlJars.getApi(ClubApi.class);
    eventApi = brawlJars.getApi(EventApi.class);
  }

  @Test
  void player_findById() throws Exception {
    PlayerResponse
        playerResponse =
        playerApi.findById(PlayerRequest.builder("#28UP80RRY").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(playerResponse);
    String expected = playerResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  private void assertDiff(String expected, String actual) {
    JsonValue source = Json.createReader(new StringReader(expected)).readValue();
    JsonValue target = Json.createReader(new StringReader(actual)).readValue();
    JsonPatch diff;
    try {
      diff = Json.createDiff(source.asJsonObject(), target.asJsonObject());
    } catch (ClassCastException e) {
      diff = Json.createDiff(source.asJsonArray(), target.asJsonArray());
    }
    StringBuilder diffOutput = new StringBuilder();
    diff.toJsonArray().forEach(entry -> diffOutput.append(entry + "\n"));
    assertEquals(EMPTY, diffOutput.toString());
    assertEquals(expected, actual);
  }

  @Test
  void player_findBattleLog() throws Exception {
    BattleLogResponse
        battleLogResponse =
        playerApi.findBattleLog(BattleLogRequest.builder("#28UP80RRY").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(battleLogResponse);
    String expected = battleLogResponse.getRawResponse().getRaw().replace(",\"map\":null", "");

    assertDiff(expected, actual);
  }

  @Test
  void club_findClub() throws Exception {
    ClubResponse clubResponse = clubApi.findClub(ClubRequest.builder("#JUURPCV9").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(clubResponse);
    String expected = clubResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void club_findClubMembers() throws Exception {
    ClubMembersResponse
        clubMembersResponse =
        clubApi.findClubMembers(ClubMembersRequest.builder("#JUURPCV9").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(clubMembersResponse);
    String expected = clubMembersResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void rankings_findPowerplayRankings() throws Exception {
    PowerplayRankingsResponse
        powerplayRankingsResponse =
        rankingApi.findPowerplayRankings(PowerplayRankingsRequest.builder("DE", 81L).storeRawResponse(true).build())
            .get();
    String actual = GSON.toJson(powerplayRankingsResponse);
    String expected = powerplayRankingsResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void rankings_findPowerplayRankingsSeasons() throws Exception {
    PowerplayRankingsSeasonsResponse
        powerplayRankingsSeasonsResponse =
        rankingApi.findPowerplayRankingsSeasons(
            PowerplayRankingsSeasonsRequest.builder("DE").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(powerplayRankingsSeasonsResponse);
    String expected = powerplayRankingsSeasonsResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void rankings_findClubRankings() throws Exception {
    ClubRankingsResponse
        clubRankingsResponse =
        rankingApi.findClubRankings(ClubRankingsRequest.builder("DE").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(clubRankingsResponse);
    String expected = clubRankingsResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void rankings_findBrawlerRankings() throws Exception {
    BrawlerRankingsResponse
        brawlerRankingsResponse =
        rankingApi.findBrawlerRankings(BrawlerRankingsRequest.builder("DE", 16000054L).storeRawResponse(true).build())
            .get();
    String actual = GSON.toJson(brawlerRankingsResponse);
    String expected = brawlerRankingsResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void rankings_findPlayerRankings() throws Exception {
    PlayerRankingsResponse
        playerRankingsResponse =
        rankingApi.findPlayerRankings(PlayerRankingsRequest.builder("DE").storeRawResponse(true).build()).get();
    String actual = GSON.toJson(playerRankingsResponse);
    String expected = playerRankingsResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void brawlers_findAll() throws Exception {
    BrawlersResponse
        brawlersResponse =
        brawlerApi.findAll(BrawlersRequest.builder().storeRawResponse(true).build()).get();
    String actual = GSON.toJson(brawlersResponse);
    String expected = brawlersResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void brawlers_findById() throws Exception {
    BrawlerResponse
        brawlerResponse =
        brawlerApi.findById(BrawlerRequest.builder(16000000L).storeRawResponse(true).build()).get();
    String actual = GSON.toJson(brawlerResponse);
    String expected = brawlerResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

  @Test
  void events_findEventRotation() throws Exception {
    EventRotationResponse
        eventRotationResponse =
        eventApi.findEventRotation(EventRotationRequest.builder().storeRawResponse(true).build()).get();
    String actual = GSON.toJson(eventRotationResponse);
    String expected = eventRotationResponse.getRawResponse().getRaw();

    assertDiff(expected, actual);
  }

}