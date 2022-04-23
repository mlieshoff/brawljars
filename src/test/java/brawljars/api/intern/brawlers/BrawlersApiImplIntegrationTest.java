package brawljars.api.intern.brawlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class BrawlersApiImplIntegrationTest extends IntegrationTestBase {

  private BrawlersApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(BrawlersApi.class);
  }

  @Test
  void findAll() throws Exception {
    BrawlersRequest brawlersRequest = BrawlersRequest.builder().build();
    prepare("/brawlers", "src/test/resources/brawlers-findAll.json");

    BrawlersResponse actual = unitUnderTest.findAll(brawlersRequest);
    BrawlersResponse expected = toJson(BrawlersResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findById() throws Exception {
    String brawlerId = "brawlerId";
    BrawlerRequest brawlersRequest = BrawlerRequest.builder(brawlerId).build();
    prepare("/brawlers/{brawlerId}".replace("{brawlerId}", brawlerId), "src/test/resources/brawlers-findById.json");

    BrawlerResponse actual = unitUnderTest.findById(brawlersRequest);
    BrawlerResponse expected = toJson(BrawlerResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}