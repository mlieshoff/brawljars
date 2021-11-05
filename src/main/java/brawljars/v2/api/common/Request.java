package brawljars.v2.api.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Request {

  @Builder(builderMethodName = "requestBuilder")
  protected Request() {
    //
  }

  public Map<String, String> getQueryParameters() {
    return new LinkedHashMap<>();
  }

  public Map<String, String> getRestParameters() {
    return new HashMap<>();
  }

}
