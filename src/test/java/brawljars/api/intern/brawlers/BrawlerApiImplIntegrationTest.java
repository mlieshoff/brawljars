package brawljars.api.intern.brawlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class BrawlerApiImplIntegrationTest extends IntegrationTestBase {

  private BrawlerApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(BrawlerApi.class);
  }

  @Test
  void findAll() throws Exception {
    brawljars.api.intern.brawlers.BrawlersRequest brawlersRequest = brawljars.api.intern.brawlers.BrawlersRequest.builder().build();
    prepare("/brawlers", "src/test/resources/brawler-findAll.json");

    brawljars.api.intern.brawlers.BrawlersResponse actual = unitUnderTest.findAll(brawlersRequest);
    brawljars.api.intern.brawlers.BrawlersResponse expected = toJson(brawljars.api.intern.brawlers.BrawlersResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findById() throws Exception {
    String brawlerId = "brawlerId";
    brawljars.api.intern.brawlers.BrawlerRequest brawlersRequest = brawljars.api.intern.brawlers.BrawlerRequest.builder(brawlerId).build();
    prepare("/brawlers/{brawlerId}".replace("{brawlerId}", brawlerId), "src/test/resources/brawler-findById.json");

    brawljars.api.intern.brawlers.BrawlerResponse actual = unitUnderTest.findById(brawlersRequest);
    brawljars.api.intern.brawlers.BrawlerResponse expected = toJson(brawljars.api.intern.brawlers.BrawlerResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}