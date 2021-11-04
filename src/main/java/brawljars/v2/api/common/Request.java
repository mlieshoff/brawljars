package brawljars.v2.api.common;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Request {

  @Builder(builderMethodName = "requestBuilder")
  Request() {
    //
  }

  public Map<String, String> getQueryParameters() {
    return new LinkedHashMap<>();
  }

}
