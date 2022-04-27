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

  @Test
  void club_findClub() {
    ClubResponse clubResponse = clubApi.findClub(ClubRequest.builder("#JUURPCV9").build());
    String actual = GSON.toJson(clubResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void club_findClubMembers() {
    ClubMembersResponse clubMembersResponse = clubApi.findClubMembers(ClubMembersRequest.builder("#JUURPCV9").build());
    String actual = GSON.toJson(clubMembersResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findPowerplayRankings() {
    PowerplayRankingsResponse
        powerplayRankingsResponse =
        rankingApi.findPowerplayRankings(PowerplayRankingsRequest.builder("DE", 81L).build());
    String actual = GSON.toJson(powerplayRankingsResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findPowerplayRankingsSeasons() {
    PowerplayRankingsSeasonsResponse
        powerplayRankingsSeasonsResponse =
        rankingApi.findPowerplayRankingsSeasons(PowerplayRankingsSeasonsRequest.builder("DE").build());
    String actual = GSON.toJson(powerplayRankingsSeasonsResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findClubRankings() {
    ClubRankingsResponse
        clubRankingsResponse =
        rankingApi.findClubRankings(ClubRankingsRequest.builder("DE").build());
    String actual = GSON.toJson(clubRankingsResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findBrawlerRankings() {
    BrawlerRankingsResponse
        brawlerRankingsResponse =
        rankingApi.findBrawlerRankings(BrawlerRankingsRequest.builder("DE", 16000054L).build());
    String actual = GSON.toJson(brawlerRankingsResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findPlayerRankings() {
    PlayerRankingsResponse
        playerRankingsResponse =
        rankingApi.findPlayerRankings(PlayerRankingsRequest.builder("DE").build());
    String actual = GSON.toJson(playerRankingsResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
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
    BrawlerResponse brawlerResponse = brawlerApi.findById(BrawlerRequest.builder(16000000L).build());
    String actual = GSON.toJson(brawlerResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void events_findEventRotation() {
    EventRotationResponse eventRotationResponse = eventApi.findEventRotation(EventRotationRequest.builder("dummy").build());
    String actual = GSON.toJson(eventRotationResponse);
    String expected = brawlerApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

}
