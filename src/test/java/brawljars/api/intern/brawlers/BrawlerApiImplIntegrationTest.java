package brawljars.api.intern.brawlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;
import brawljars.common.BlockingCallback;

public class BrawlerApiImplIntegrationTest extends IntegrationTestBase {

  private BrawlerApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(BrawlerApi.class);
  }

  @Test
  void findAll() throws Exception {
    run_findAll(false);
  }

  void run_findAll(boolean withCallback) throws Exception {
    brawljars.api.intern.brawlers.BrawlersRequest.BrawlersRequestBuilder builder = brawljars.api.intern.brawlers.BrawlersRequest.builder()
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.brawlers.BrawlersRequest request = builder.build();
    prepare("/brawlers", "src/test/resources/brawler-findAll.json", request);

    brawljars.api.intern.brawlers.BrawlersResponse actual;
    if (withCallback) {
      unitUnderTest.findAll(request);
      actual = ((BlockingCallback<brawljars.api.intern.brawlers.BrawlersResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findAll(request);
    }
    brawljars.api.intern.brawlers.BrawlersResponse expected = toJson(brawljars.api.intern.brawlers.BrawlersResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findAll_withCallback() throws Exception {
    run_findAll(true);
  }

  @Test
  void findById() throws Exception {
    run_findById(false);
  }

  void run_findById(boolean withCallback) throws Exception {
    long brawlerId = 4711L;
    brawljars.api.intern.brawlers.BrawlerRequest.BrawlerRequestBuilder builder = brawljars.api.intern.brawlers.BrawlerRequest.builder(brawlerId);
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.brawlers.BrawlerRequest request = builder.build();
    prepare("/brawlers/{brawlerId}".replace("{brawlerId}", String.valueOf(brawlerId)), "src/test/resources/brawler-findById.json", request);

    brawljars.api.intern.brawlers.BrawlerResponse actual;
    if (withCallback) {
      unitUnderTest.findById(request);
      actual = ((BlockingCallback<brawljars.api.intern.brawlers.BrawlerResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findById(request);
    }
    brawljars.api.intern.brawlers.BrawlerResponse expected = toJson(brawljars.api.intern.brawlers.BrawlerResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findById_withCallback() throws Exception {
    run_findById(true);
  }

}