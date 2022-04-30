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
  brawljars.api.intern.brawlers.BrawlersRequest request = brawljars.api.intern.brawlers.BrawlersRequest.builder()
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/brawlers", "src/test/resources/brawler-findAll.json", request);

    brawljars.api.intern.brawlers.BrawlersResponse actual = unitUnderTest.findAll(request).get();
    brawljars.api.intern.brawlers.BrawlersResponse expected = toJson(brawljars.api.intern.brawlers.BrawlersResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findById() throws Exception {
    long brawlerId = 4711L;
  brawljars.api.intern.brawlers.BrawlerRequest request = brawljars.api.intern.brawlers.BrawlerRequest.builder(brawlerId)
      .build();
    prepare("/brawlers/{brawlerId}".replace("{brawlerId}", String.valueOf(brawlerId)), "src/test/resources/brawler-findById.json", request);

    brawljars.api.intern.brawlers.BrawlerResponse actual = unitUnderTest.findById(request).get();
    brawljars.api.intern.brawlers.BrawlerResponse expected = toJson(brawljars.api.intern.brawlers.BrawlerResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}