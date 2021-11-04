package brawljars.v2.api;

import brawljars.v2.api.common.PaginationRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestContext {

  private final String url;
  private final String apiKey;

  private final PaginationRequest paginationRequest;

  private final Class<?> responseClass;

}
