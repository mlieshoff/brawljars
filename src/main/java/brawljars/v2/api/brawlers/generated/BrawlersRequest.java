package brawljars.v2.api.brawlers.generated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BrawlersRequest {

  private final String before;
  private final String after;

  private final int limit;

}
