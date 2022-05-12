package brawljars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import brawljars.api.intern.players.PlayerApi;
import brawljars.connector.Connector;

@ExtendWith(MockitoExtension.class)
class BrawlJarsTest {

  private static final String URL = "url";
  private static final String API_KEY = "apiKey";

  private BrawlJars unitUnderTest;

  @Mock
  private Connector connector;

  @BeforeEach
  void setUp() {
    unitUnderTest = new BrawlJars(URL, API_KEY, connector);
  }

  @Test
  void listApis_whenCalled_shouldReturnListOfApiInterfaceNames() {
    Set<String>
        expected =
        new HashSet<>(Arrays.asList("brawljars.api.intern.players.PlayerApi", "brawljars.api.intern.club.ClubApi",
            "brawljars.api.intern.brawlers.BrawlerApi", "brawljars.api.intern.event.EventApi",
            "brawljars.api.intern.rankings.RankingApi"));

    List<String> actual = unitUnderTest.listApis();

    assertEquals(expected, new HashSet<>(actual));
  }

  @Test
  void getApi_whenWithValidParameter_shouldReturnApiInstance() {
    PlayerApi actual = unitUnderTest.getApi(PlayerApi.class);

    assertNotNull(actual);
  }

}