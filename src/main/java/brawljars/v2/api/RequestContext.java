package brawljars.v2.api;

import brawljars.v2.api.common.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestContext {

  private final String url;
  private final String apiKey;

  private final Request request;

  private final Class<?> responseClass;

}
