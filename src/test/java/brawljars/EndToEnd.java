package brawljars;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
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
  void player_findById() {
    PlayerResponse
        playerResponse =
        playerApi.findById(PlayerRequest.builder("#28UP80RRY").storeRawResponse(true).build());
    String actual = GSON.toJson(playerResponse);
    String expected = playerResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void player_findBattleLog() {
    BattleLogResponse
        battleLogResponse =
        playerApi.findBattleLog(BattleLogRequest.builder("#28UP80RRY").storeRawResponse(true).build());
    String actual = GSON.toJson(battleLogResponse);
    String expected = battleLogResponse.getRawResponse().getRaw().replace(",\"map\":null", "");

    assertEquals(expected, actual);
  }

  @Test
  void club_findClub() {
    ClubResponse clubResponse = clubApi.findClub(ClubRequest.builder("#JUURPCV9").storeRawResponse(true).build());
    String actual = GSON.toJson(clubResponse);
    String expected = clubResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void club_findClubMembers() {
    ClubMembersResponse
        clubMembersResponse =
        clubApi.findClubMembers(ClubMembersRequest.builder("#JUURPCV9").storeRawResponse(true).build());
    String actual = GSON.toJson(clubMembersResponse);
    String expected = clubMembersResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findPowerplayRankings() {
    PowerplayRankingsResponse
        powerplayRankingsResponse =
        rankingApi.findPowerplayRankings(PowerplayRankingsRequest.builder("DE", 81L).storeRawResponse(true).build());
    String actual = GSON.toJson(powerplayRankingsResponse);
    String expected = powerplayRankingsResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findPowerplayRankingsSeasons() {
    PowerplayRankingsSeasonsResponse
        powerplayRankingsSeasonsResponse =
        rankingApi.findPowerplayRankingsSeasons(
            PowerplayRankingsSeasonsRequest.builder("DE").storeRawResponse(true).build());
    String actual = GSON.toJson(powerplayRankingsSeasonsResponse);
    String expected = powerplayRankingsSeasonsResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findClubRankings() {
    ClubRankingsResponse
        clubRankingsResponse =
        rankingApi.findClubRankings(ClubRankingsRequest.builder("DE").storeRawResponse(true).build());
    String actual = GSON.toJson(clubRankingsResponse);
    String expected = clubRankingsResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findBrawlerRankings() {
    BrawlerRankingsResponse
        brawlerRankingsResponse =
        rankingApi.findBrawlerRankings(BrawlerRankingsRequest.builder("DE", 16000054L).storeRawResponse(true).build());
    String actual = GSON.toJson(brawlerRankingsResponse);
    String expected = brawlerRankingsResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findPlayerRankings() {
    PlayerRankingsResponse
        playerRankingsResponse =
        rankingApi.findPlayerRankings(PlayerRankingsRequest.builder("DE").storeRawResponse(true).build());
    String actual = GSON.toJson(playerRankingsResponse);
    String expected = playerRankingsResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void brawlers_findAll() throws ExecutionException, InterruptedException {
    BrawlersResponse brawlersResponse = brawlerApi.findAll(BrawlersRequest.builder().storeRawResponse(true).build());
    String actual = GSON.toJson(brawlersResponse);
    String expected = brawlersResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void brawlers_findById() {
    BrawlerResponse
        brawlerResponse =
        brawlerApi.findById(BrawlerRequest.builder(16000000L).storeRawResponse(true).build());
    String actual = GSON.toJson(brawlerResponse);
    String expected = brawlerResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void events_findEventRotation() {
    EventRotationResponse
        eventRotationResponse =
        eventApi.findEventRotation(EventRotationRequest.builder().storeRawResponse(true).build());
    String actual = GSON.toJson(eventRotationResponse);
    String expected = eventRotationResponse.getRawResponse().getRaw();

    assertEquals(expected, actual);
  }

}
