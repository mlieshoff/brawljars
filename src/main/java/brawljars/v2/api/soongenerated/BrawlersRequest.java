package brawljars.v2.api.soongenerated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrawlersRequest {

  private String before;
  private String after;

  private int limit;

}
