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
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import brawljars.connector.StandardConnector;

public class IntegrationTestBase {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  private static final ThreadLocal<String> expected = new ThreadLocal<>();

  private static WireMockServer wireMockServer;

  BrawlJars brawlJars;

  @BeforeAll
  public static void beforeAll() {
    wireMockServer = new WireMockServer();
    wireMockServer.start();
  }

  @BeforeEach
  public void beforeEach() {
    configureFor("localhost", 8080);
    brawlJars = createBrawlJars();
  }

  @AfterEach
  public void afterEach() {
    wireMockServer.resetAll();
    brawlJars = createBrawlJars();
  }

  private BrawlJars createBrawlJars() {
    return new BrawlJars("http://localhost:8080", "myApiKey", new StandardConnector());
  }

  protected String getExpected() {
    return expected.get();
  }

  protected String json(Object o) {
    return GSON.toJson(o);
  }

  protected String body(String filename) throws IOException {
    String s = readFileToString(new File(filename));
    expected.set(s);
    return s;
  }

  protected void prepare(String url, String filename) throws Exception {
    stubFor(get(urlEqualTo(url))
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
