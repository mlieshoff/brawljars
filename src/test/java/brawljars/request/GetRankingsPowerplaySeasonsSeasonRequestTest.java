package brawljars.request;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetRankingsPowerplaySeasonsSeasonRequestTest
    extends PageableRequestTestBase<GetRankingsPowerplaySeasonsSeasonRequest> {

  public static final String COUNTRY_CODE = "countryCode";
  public static final String SEASON_ID = "seasonId";

  @Test
  void build_whenWithNullCountryCode_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class,
        () -> GetRankingsPowerplaySeasonsSeasonRequest.builder(null, SEASON_ID).build());
  }

  @Test
  void build_whenWithEmptyCountryCode_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class,
        () -> GetRankingsPowerplaySeasonsSeasonRequest.builder("", SEASON_ID).build());
  }

  @Test
  void build_whenWithCountryCode_thenSetValue() throws Exception {

    assertEquals(COUNTRY_CODE,
        GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID).build().getCountryCode());
  }

  @Test
  void build_whenWithNullSeasonId_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class,
        () -> GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, null).build());
  }

  @Test
  void build_whenWithEmptySeasonId_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class,
        () -> GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, "").build());
  }

  @Test
  void build_whenWithSeasonId_thenSetValue() throws Exception {

    assertEquals(SEASON_ID,
        GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID).build().getSeasonId());
  }

  @Test
  void build_whenWithValues_thenHaveRestParameter() throws Exception {

    assertEquals(asList(COUNTRY_CODE, SEASON_ID),
        GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetRankingsPowerplaySeasonsSeasonRequest.builder(COUNTRY_CODE, SEASON_ID);
  }

}