package brawljars.api.intern.rankings;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest powerplayRankingsRequest = brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest.builder(countryCode, seasonId)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/rankings/{countryCode}/powerplay/seasons/{seasonId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{seasonId}", String.valueOf(seasonId)), "src/test/resources/ranking-findPowerplayRankings.json", powerplayRankingsRequest);

    brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse actual = unitUnderTest.findPowerplayRankings(powerplayRankingsRequest);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse expected = toJson(brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findPowerplayRankingsSeasons() throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest = brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest.builder(countryCode)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/rankings/{countryCode}/powerplay/seasons".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findPowerplayRankingsSeasons.json", powerplayRankingsSeasonsRequest);

    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse actual = unitUnderTest.findPowerplayRankingsSeasons(powerplayRankingsSeasonsRequest);
    brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse expected = toJson(brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findClubRankings() throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.club.ClubRankingsRequest clubRankingsRequest = brawljars.api.intern.rankings.club.ClubRankingsRequest.builder(countryCode)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/rankings/{countryCode}/clubs".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findClubRankings.json", clubRankingsRequest);

    brawljars.api.intern.rankings.club.ClubRankingsResponse actual = unitUnderTest.findClubRankings(clubRankingsRequest);
    brawljars.api.intern.rankings.club.ClubRankingsResponse expected = toJson(brawljars.api.intern.rankings.club.ClubRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findBrawlerRankings() throws Exception {
    String countryCode = "countryCode";
    long brawlerId = 4711L;
    brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest brawlerRankingsRequest = brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest.builder(countryCode, brawlerId)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/rankings/{countryCode}/brawlers/{brawlerId}".replace("{countryCode}", String.valueOf(countryCode)).replace("{brawlerId}", String.valueOf(brawlerId)), "src/test/resources/ranking-findBrawlerRankings.json", brawlerRankingsRequest);

    brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse actual = unitUnderTest.findBrawlerRankings(brawlerRankingsRequest);
    brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse expected = toJson(brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findPlayerRankings() throws Exception {
    String countryCode = "countryCode";
    brawljars.api.intern.rankings.player.PlayerRankingsRequest playerRankingsRequest = brawljars.api.intern.rankings.player.PlayerRankingsRequest.builder(countryCode)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/rankings/{countryCode}/players".replace("{countryCode}", String.valueOf(countryCode)), "src/test/resources/ranking-findPlayerRankings.json", playerRankingsRequest);

    brawljars.api.intern.rankings.player.PlayerRankingsResponse actual = unitUnderTest.findPlayerRankings(playerRankingsRequest);
    brawljars.api.intern.rankings.player.PlayerRankingsResponse expected = toJson(brawljars.api.intern.rankings.player.PlayerRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}