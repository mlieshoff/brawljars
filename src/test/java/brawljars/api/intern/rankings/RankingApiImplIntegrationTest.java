package brawljars.api.intern.rankings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;
import brawljars.common.BlockingCallback;

public class RankingApiImplIntegrationTest extends IntegrationTestBase {

  private RankingApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(RankingApi.class);
  }

  @Test
  void findPowerplayRankings() throws Exception {
    run_findPowerplayRankings(false);
  }

  void run_findPowerplayRankings(boolean withCallback) throws Exception {
    String countryCode = "countryCode";
    long seasonId = 4711L;
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.PowerplayRankingsRequestBuilder builder = brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.builder(countryCode, seasonId)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest request = builder.build();
    prepare("/rankings/{countryCode}/powerplay/seasons/{seasonId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{seasonId}", String.valueOf(seasonId)), "src/test/resources/ranking-findPowerplayRankings.json", request);

    brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse actual;
    if (withCallback) {
      unitUnderTest.findPowerplayRankings(request);
      actual = ((BlockingCallback<brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findPowerplayRankings(request);
    }
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse expected = toJson(brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findPowerplayRankings_withCallback() throws Exception {
    run_findPowerplayRankings(true);
  }

  @Test
  void findPowerplayRankingsSeasons() throws Exception {
    run_findPowerplayRankingsSeasons(false);
  }

  void run_findPowerplayRankingsSeasons(boolean withCallback) throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.PowerplayRankingsSeasonsRequestBuilder builder = brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.builder(countryCode)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest request = builder.build();
    prepare("/rankings/{countryCode}/powerplay/seasons".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findPowerplayRankingsSeasons.json", request);

    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse actual;
    if (withCallback) {
      unitUnderTest.findPowerplayRankingsSeasons(request);
      actual = ((BlockingCallback<brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findPowerplayRankingsSeasons(request);
    }
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse expected = toJson(brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findPowerplayRankingsSeasons_withCallback() throws Exception {
    run_findPowerplayRankingsSeasons(true);
  }

  @Test
  void findClubRankings() throws Exception {
    run_findClubRankings(false);
  }

  void run_findClubRankings(boolean withCallback) throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.club.ClubRankingsRequest.ClubRankingsRequestBuilder builder = brawljars.api.intern.rankings.club.ClubRankingsRequest.builder(countryCode)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.rankings.club.ClubRankingsRequest request = builder.build();
    prepare("/rankings/{countryCode}/clubs".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findClubRankings.json", request);

    brawljars.api.intern.rankings.club.ClubRankingsResponse actual;
    if (withCallback) {
      unitUnderTest.findClubRankings(request);
      actual = ((BlockingCallback<brawljars.api.intern.rankings.club.ClubRankingsResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findClubRankings(request);
    }
    brawljars.api.intern.rankings.club.ClubRankingsResponse expected = toJson(brawljars.api.intern.rankings.club.ClubRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findClubRankings_withCallback() throws Exception {
    run_findClubRankings(true);
  }

  @Test
  void findBrawlerRankings() throws Exception {
    run_findBrawlerRankings(false);
  }

  void run_findBrawlerRankings(boolean withCallback) throws Exception {
    String countryCode = "countryCode";
    long brawlerId = 4711L;
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.BrawlerRankingsRequestBuilder builder = brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.builder(countryCode, brawlerId)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest request = builder.build();
    prepare("/rankings/{countryCode}/brawlers/{brawlerId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{brawlerId}", String.valueOf(brawlerId)), "src/test/resources/ranking-findBrawlerRankings.json", request);

    brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse actual;
    if (withCallback) {
      unitUnderTest.findBrawlerRankings(request);
      actual = ((BlockingCallback<brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findBrawlerRankings(request);
    }
    brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse expected = toJson(brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findBrawlerRankings_withCallback() throws Exception {
    run_findBrawlerRankings(true);
  }

  @Test
  void findPlayerRankings() throws Exception {
    run_findPlayerRankings(false);
  }

  void run_findPlayerRankings(boolean withCallback) throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.player.PlayerRankingsRequest.PlayerRankingsRequestBuilder builder = brawljars.api.intern.rankings.player.PlayerRankingsRequest.builder(countryCode)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.rankings.player.PlayerRankingsRequest request = builder.build();
    prepare("/rankings/{countryCode}/players".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findPlayerRankings.json", request);

    brawljars.api.intern.rankings.player.PlayerRankingsResponse actual;
    if (withCallback) {
      unitUnderTest.findPlayerRankings(request);
      actual = ((BlockingCallback<brawljars.api.intern.rankings.player.PlayerRankingsResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findPlayerRankings(request);
    }
    brawljars.api.intern.rankings.player.PlayerRankingsResponse expected = toJson(brawljars.api.intern.rankings.player.PlayerRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findPlayerRankings_withCallback() throws Exception {
    run_findPlayerRankings(true);
  }

}