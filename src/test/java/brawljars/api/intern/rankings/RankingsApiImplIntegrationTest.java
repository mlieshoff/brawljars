package brawljars.api.intern.rankings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class RankingsApiImplIntegrationTest extends IntegrationTestBase {

  private RankingsApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(RankingsApi.class);
  }

  @Test
  void findPowerplayRankings() throws Exception {
    String countryCode = "countryCode";
    String seasonId = "seasonId";
    PowerplayRankingsRequest powerplayRankingsRequest = PowerplayRankingsRequest.builder(countryCode, seasonId).build();
    prepare("/rankings/{countryCode}/powerplay/seasons/{seasonId}".replace("{countryCode}", countryCode).replace("{seasonId}", seasonId), "src/test/resources/rankings-findPowerplayRankings.json");

    PowerplayRankingsResponse actual = unitUnderTest.findPowerplayRankings(powerplayRankingsRequest);

    assertEquals(getExpected(), json(actual));
  }

}