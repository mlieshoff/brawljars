package brawljars.v2.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiContext {

  private final String url;
  private final String apiKey;

  private final Connector connector;

}
