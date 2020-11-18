package brawljars.request;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetRankingsBrawlerRequestTest extends PageableRequestTestBase<GetRankingsBrawlerRequest> {

  public static final String COUNTRY_CODE = "countryCode";
  public static final String BRAWLER_ID = "brawlerId";

  @Test
  void build_whenWithNullCountryCode_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetRankingsBrawlerRequest.builder(null, BRAWLER_ID).build());
  }

  @Test
  void build_whenWithEmptyCountryCode_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetRankingsBrawlerRequest.builder("", BRAWLER_ID).build());
  }

  @Test
  void build_whenWithCountryCode_thenSetValue() throws Exception {

    assertEquals(COUNTRY_CODE, GetRankingsBrawlerRequest.builder(COUNTRY_CODE, BRAWLER_ID).build().getCountryCode());
  }

  @Test
  void build_whenWithNullBrawlerId_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetRankingsBrawlerRequest.builder(COUNTRY_CODE, null).build());
  }

  @Test
  void build_whenWithEmptyBrawlerId_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetRankingsBrawlerRequest.builder(COUNTRY_CODE, "").build());
  }

  @Test
  void build_whenWithBrawlerId_thenSetValue() throws Exception {

    assertEquals(BRAWLER_ID, GetRankingsBrawlerRequest.builder(COUNTRY_CODE, BRAWLER_ID).build().getBrawlerId());
  }

  @Test
  void build_whenWithValues_thenHaveRestParameter() throws Exception {

    assertEquals(asList(COUNTRY_CODE, BRAWLER_ID),
        GetRankingsBrawlerRequest.builder(COUNTRY_CODE, BRAWLER_ID).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetRankingsBrawlerRequest.builder(COUNTRY_CODE, BRAWLER_ID);
  }

}