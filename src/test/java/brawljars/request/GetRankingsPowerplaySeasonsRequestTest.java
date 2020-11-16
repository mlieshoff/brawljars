package brawljars.request;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetRankingsPowerplaySeasonsRequestTest extends PageableRequestTestBase<GetPlayerBattleLogRequest> {

  public static final String COUNTRY_CODE = "countryCode";

  @Test
  void build_whenWithNullPlayerTag_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetRankingsPowerplaySeasonsRequest.builder(null).build());
  }

  @Test
  void build_whenWithEmptyPlayerTag_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetRankingsPowerplaySeasonsRequest.builder("").build());
  }

  @Test
  void build_whenWithPlayerTag_thenSetValue() throws Exception {

    assertEquals(COUNTRY_CODE, GetRankingsPowerplaySeasonsRequest.builder(COUNTRY_CODE).build().getCountryCode());
  }

  @Test
  void build_whenWithPlayerTag_thenHaveRestParameter() throws Exception {

    assertEquals(singletonList(COUNTRY_CODE), GetRankingsPowerplaySeasonsRequest.builder(COUNTRY_CODE).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetRankingsPowerplaySeasonsRequest.builder(COUNTRY_CODE);
  }

}