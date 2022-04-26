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
    String seasonId = "seasonId";
    brawljars.api.intern.rankings.PowerplayRankingsRequest powerplayRankingsRequest = brawljars.api.intern.rankings.PowerplayRankingsRequest.builder(countryCode, seasonId).build();
    prepare("/rankings/{countryCode}/powerplay/seasons/{seasonId}".replace("{countryCode}", countryCode).replace("{seasonId}", seasonId), "src/test/resources/ranking-findPowerplayRankings.json");

    brawljars.api.intern.rankings.PowerplayRankingsResponse actual = unitUnderTest.findPowerplayRankings(powerplayRankingsRequest);
    brawljars.api.intern.rankings.PowerplayRankingsResponse expected = toJson(brawljars.api.intern.rankings.PowerplayRankingsResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}