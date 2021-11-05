package brawljars.v2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class SampleTest {

  @BeforeAll
  void setUp() {
    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
    wireMockServer.start();
  }

  @AfterEach
  void setUp() {
    wireMockServer.resetAll();
  }


}
