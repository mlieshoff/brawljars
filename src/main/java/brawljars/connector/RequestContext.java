package brawljars.connector;

import brawljars.common.Request;
import lombok.Data;
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
