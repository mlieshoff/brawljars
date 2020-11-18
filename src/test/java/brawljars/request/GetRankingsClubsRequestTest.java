package brawljars.request;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetRankingsClubsRequestTest extends PageableRequestTestBase<GetRankingsClubsRequest> {

  public static final String COUNTRY_CODE = "countryCode";

  @Test
  void build_whenWithNullCountryCode_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetRankingsClubsRequest.builder(null).build());
  }

  @Test
  void build_whenWithEmptyCountryCode_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetRankingsClubsRequest.builder("").build());
  }

  @Test
  void build_whenWithCountryCode_thenSetValue() throws Exception {

    assertEquals(COUNTRY_CODE, GetRankingsClubsRequest.builder(COUNTRY_CODE).build().getCountryCode());
  }

  @Test
  void build_whenWithCountryCode_thenHaveRestParameter() throws Exception {

    assertEquals(singletonList(COUNTRY_CODE),
        GetRankingsClubsRequest.builder(COUNTRY_CODE).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetRankingsClubsRequest.builder(COUNTRY_CODE);
  }

}