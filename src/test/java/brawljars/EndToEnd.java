package brawljars;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.api.intern.brawlers.BrawlerRequest;
import brawljars.api.intern.brawlers.BrawlerResponse;
import brawljars.api.intern.brawlers.BrawlersApi;
import brawljars.api.intern.brawlers.BrawlersRequest;
import brawljars.api.intern.brawlers.BrawlersResponse;
import brawljars.api.intern.rankings.PowerplayRankingsRequest;
import brawljars.api.intern.rankings.PowerplayRankingsResponse;
import brawljars.api.intern.rankings.RankingsApi;
import brawljars.connector.StandardConnector;

public class EndToEnd {

  private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

  private BrawlJars brawlJars;

  private BrawlersApi brawlersApi;

  private RankingsApi rankingsApi;

  @BeforeEach
  void setUp() {
    brawlJars =
        new BrawlJars("https://bsproxy.royaleapi.dev/v1", System.getProperty("apiKey"), new StandardConnector());
    brawlersApi = brawlJars.getApi(BrawlersApi.class);
    rankingsApi = brawlJars.getApi(RankingsApi.class);
  }

  @Test
  void brawlers_findAll() {
    BrawlersResponse brawlersResponse = brawlersApi.findAll(BrawlersRequest.builder().build());
    String actual = GSON.toJson(brawlersResponse);
    String expected = brawlersApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void brawlers_findById() {
    BrawlerResponse brawlerResponse = brawlersApi.findById(BrawlerRequest.builder("16000000").build());
    String actual = GSON.toJson(brawlerResponse);
    String expected = brawlersApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

  @Test
  void rankings_findById() {
    PowerplayRankingsResponse
        powerplayRankingsResponse =
        rankingsApi.findPowerplayRankings(PowerplayRankingsRequest.builder("DE", "81").build());
    String actual = GSON.toJson(powerplayRankingsResponse);
    String expected = brawlersApi.getLastResponse().getRaw();

    assertEquals(expected, actual);
  }

}
