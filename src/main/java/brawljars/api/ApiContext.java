package brawljars.api;

import static brawljars.common.Utils.require;

import brawljars.connector.Connector;
import lombok.Getter;

@Getter
public class ApiContext {

  private final String url;
  private final String apiKey;

  private final Connector connector;

  public ApiContext(String url, String apiKey, Connector connector) {
    require("url", url);
    require("apiKey", apiKey);
    require("connector", connector);
    this.url = url;
    this.apiKey = apiKey;
    this.connector = connector;
  }

}
