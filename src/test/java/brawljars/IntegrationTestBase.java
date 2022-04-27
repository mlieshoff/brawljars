package brawljars;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.apache.commons.io.FileUtils.readFileToString;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import brawljars.common.IResponse;
import brawljars.common.PaginationRequest;
import brawljars.common.Request;
import brawljars.connector.StandardConnector;

public class IntegrationTestBase {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

  private static final ThreadLocal<String> expected = new ThreadLocal<>();

  private static WireMockServer wireMockServer;

  BrawlJars brawlJars;

  @BeforeAll
  public static void beforeAll() {
    WireMockConfiguration wireMockConfiguration = new WireMockConfiguration().dynamicPort();
    wireMockServer = new WireMockServer(wireMockConfiguration);
    wireMockServer.start();
  }

  @BeforeEach
  public void beforeEach() {
    configureFor("localhost", wireMockServer.port());
    brawlJars = createBrawlJars();
  }

  @AfterEach
  public void afterEach() {
    wireMockServer.resetAll();
    brawlJars = createBrawlJars();
  }

  private BrawlJars createBrawlJars() {
    return new BrawlJars("http://localhost:" + wireMockServer.port(), "myApiKey", new StandardConnector());
  }

  protected String getExpected() {
    return expected.get();
  }

  protected String json(Object o) {
    return GSON.toJson(o);
  }

  protected <T> T toJson(Class<T> clazz, String s) {
    return GSON.fromJson(s, clazz);
  }

  protected String body(String filename) throws IOException {
    String s = readFileToString(new File(filename));
    expected.set(s);
    return s;
  }

  protected <T extends IResponse> void prepare(String url, String filename, Request<T> request) throws Exception {
    String expectedUrl = url;
    if (request instanceof PaginationRequest) {
      expectedUrl += "?limit=100&after=aaa&before=zzz";
    }
    stubFor(get(urlEqualTo(expectedUrl))
        .withHeader(HttpHeaders.AUTHORIZATION, equalTo("Bearer myApiKey"))
        .willReturn(
            aResponse()
                .withBody(body(filename))
                .withStatus(HttpStatus.SC_OK)
        )
    );
  }

  protected BrawlJars getBrawlJars() {
    return brawlJars;
  }

}
