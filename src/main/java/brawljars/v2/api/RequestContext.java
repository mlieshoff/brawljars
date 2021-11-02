package brawljars.v2.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestContext {

  private final String url;
  private final String apiKey;

}
