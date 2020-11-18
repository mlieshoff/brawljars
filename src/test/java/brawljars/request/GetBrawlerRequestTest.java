package brawljars.request;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetBrawlerRequestTest extends RequestTestBase {

  public static final String BRAWLER_ID = "brawlerId";

  @Test
  void build_whenWithNullBrawlerId_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetBrawlerRequest.builder(null).build());
  }

  @Test
  void build_whenWithEmptyBrawlerId_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetBrawlerRequest.builder("").build());
  }

  @Test
  void build_whenWithBrawlerId_thenSetValue() throws Exception {

    assertEquals(BRAWLER_ID, GetBrawlerRequest.builder(BRAWLER_ID).build().getBrawlerId());
  }

  @Test
  void build_whenWithBrawlerId_thenHaveRestParameter() throws Exception {

    assertEquals(asList(BRAWLER_ID), GetBrawlerRequest.builder(BRAWLER_ID).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetBrawlerRequest.builder(BRAWLER_ID);
  }

}