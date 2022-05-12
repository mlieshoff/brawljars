package brawljars;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.util.Collections.emptyMap;
import static org.apache.commons.io.FileUtils.readLines;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
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
    List<String> lines = readLines(new File(filename), StandardCharsets.UTF_8);
    String s = lines.stream().map(String::trim).collect(Collectors.joining());
    expected.set(s);
    return s;
  }

  protected void prepare(String url, String filename, Request request) throws Exception {
    stubFor(get(urlEqualTo(createUrl(url, request)))
        .withHeader(HttpHeaders.AUTHORIZATION, equalTo("Bearer myApiKey"))
        .willReturn(
            aResponse()
                .withBody(body(filename))
                .withStatus(HttpStatus.SC_OK)
        )
    );
  }

  private String createUrl(String url, Request request) {
    if (request instanceof PaginationRequest) {
      return url + "?limit=100&after=aaa&before=zzz";
    }
    return url;
  }

  protected void run(IResponse expected, ResultTestRunner<? extends IResponse> resultTestRunner) throws Exception {
    IResponse actual = resultTestRunner.execute();
    assertEquals(expected, actual);
    assertNotNull(actual.getRawResponse());
    assertNotEquals(emptyMap(), actual.getRawResponse().getResponseHeaders());
    assertEquals(getExpected(), actual.getRawResponse().getRaw());
    assertNotEquals(EMPTY, actual.toString());
  }

  protected void prepareWithErrorAndRun(String url, Request request, TestRunner testRunner) {
    prepareWithError(url, request);
    try {
      testRunner.execute();
      fail();
    } catch (Exception e) {
      assertEquals("brawljars.connector.ConnectorException: HTTP/1.1 400 Bad Request", e.getMessage());
    }
  }

  private void prepareWithError(String url, Request request) {
    stubFor(get(urlEqualTo(createUrl(url, request)))
        .withHeader(HttpHeaders.AUTHORIZATION, equalTo("Bearer myApiKey"))
        .willReturn(
            aResponse()
                .withBody("body")
                .withStatus(HttpStatus.SC_BAD_REQUEST)
        )
    );
  }

  public interface TestRunner {
    void execute() throws Exception;
  }

  public interface ResultTestRunner<T extends IResponse> {
    T execute() throws Exception;
  }

  protected BrawlJars getBrawlJars() {
    return brawlJars;
  }

}
