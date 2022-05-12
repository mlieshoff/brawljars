package brawljars.api.intern.rankings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class RankingApiImplIntegrationTest extends IntegrationTestBase {

  private RankingApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(RankingApi.class);
  }

  @Test
  void findPowerplayRankings() throws Exception {
    String countryCode = "countryCode";
    long seasonId = 4711L;
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.PowerplayRankingsRequestBuilder builder = brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.builder(countryCode, seasonId);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/rankings/{countryCode}/powerplay/seasons/{seasonId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{seasonId}", String.valueOf(seasonId)), "src/test/resources/ranking-findPowerplayRankings.json", request);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse expected = toJson(brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findPowerplayRankings(request).get());
  }

  @Test
  void findPowerplayRankings_whenWithException() {
    String countryCode = "countryCode";
    long seasonId = 4711L;
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.PowerplayRankingsRequestBuilder builder = brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.builder(countryCode, seasonId);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/rankings/{countryCode}/powerplay/seasons/{seasonId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{seasonId}", String.valueOf(seasonId)), request, () -> unitUnderTest.findPowerplayRankings(request).get());
  }

  @Test
  void findPowerplayRankingsSeasons() throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.PowerplayRankingsSeasonsRequestBuilder builder = brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.builder(countryCode);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/rankings/{countryCode}/powerplay/seasons".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findPowerplayRankingsSeasons.json", request);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse expected = toJson(brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findPowerplayRankingsSeasons(request).get());
  }

  @Test
  void findPowerplayRankingsSeasons_whenWithException() {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.PowerplayRankingsSeasonsRequestBuilder builder = brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.builder(countryCode);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/rankings/{countryCode}/powerplay/seasons".replace("{countryCode}", String.valueOf(countryCode)), request, () -> unitUnderTest.findPowerplayRankingsSeasons(request).get());
  }

  @Test
  void findClubRankings() throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.club.ClubRankingsRequest.ClubRankingsRequestBuilder builder = brawljars.api.intern.rankings.club.ClubRankingsRequest.builder(countryCode);
    brawljars.api.intern.rankings.club.ClubRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/rankings/{countryCode}/clubs".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findClubRankings.json", request);
    brawljars.api.intern.rankings.club.ClubRankingsResponse expected = toJson(brawljars.api.intern.rankings.club.ClubRankingsResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findClubRankings(request).get());
  }

  @Test
  void findClubRankings_whenWithException() {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.club.ClubRankingsRequest.ClubRankingsRequestBuilder builder = brawljars.api.intern.rankings.club.ClubRankingsRequest.builder(countryCode);
    brawljars.api.intern.rankings.club.ClubRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/rankings/{countryCode}/clubs".replace("{countryCode}", String.valueOf(countryCode)), request, () -> unitUnderTest.findClubRankings(request).get());
  }

  @Test
  void findBrawlerRankings() throws Exception {
    String countryCode = "countryCode";
    long brawlerId = 4711L;
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.BrawlerRankingsRequestBuilder builder = brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.builder(countryCode, brawlerId);
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/rankings/{countryCode}/brawlers/{brawlerId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{brawlerId}", String.valueOf(brawlerId)), "src/test/resources/ranking-findBrawlerRankings.json", request);
    brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse expected = toJson(brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findBrawlerRankings(request).get());
  }

  @Test
  void findBrawlerRankings_whenWithException() {
    String countryCode = "countryCode";
    long brawlerId = 4711L;
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.BrawlerRankingsRequestBuilder builder = brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.builder(countryCode, brawlerId);
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/rankings/{countryCode}/brawlers/{brawlerId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{brawlerId}", String.valueOf(brawlerId)), request, () -> unitUnderTest.findBrawlerRankings(request).get());
  }

  @Test
  void findPlayerRankings() throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.player.PlayerRankingsRequest.PlayerRankingsRequestBuilder builder = brawljars.api.intern.rankings.player.PlayerRankingsRequest.builder(countryCode);
    brawljars.api.intern.rankings.player.PlayerRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/rankings/{countryCode}/players".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findPlayerRankings.json", request);
    brawljars.api.intern.rankings.player.PlayerRankingsResponse expected = toJson(brawljars.api.intern.rankings.player.PlayerRankingsResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findPlayerRankings(request).get());
  }

  @Test
  void findPlayerRankings_whenWithException() {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.player.PlayerRankingsRequest.PlayerRankingsRequestBuilder builder = brawljars.api.intern.rankings.player.PlayerRankingsRequest.builder(countryCode);
    brawljars.api.intern.rankings.player.PlayerRankingsRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/rankings/{countryCode}/players".replace("{countryCode}", String.valueOf(countryCode)), request, () -> unitUnderTest.findPlayerRankings(request).get());
  }

}