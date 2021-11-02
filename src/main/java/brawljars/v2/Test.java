package brawljars.v2;

import brawljars.v2.api.BrawlJars;
import brawljars.v2.api.BrawlerApi;

public class Test {

  public static void main(String[] args) {
    BrawlJars brawlJarsApi = new BrawlJars("", "");
    brawlJarsApi.getApi(BrawlerApi.class);
  }
}
