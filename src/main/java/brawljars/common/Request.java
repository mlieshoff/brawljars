package brawljars.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Request<T extends IResponse> {

  private final boolean storeRawResponse;

  @Builder(builderMethodName = "requestBuilder")
  protected Request(boolean storeRawResponse) {
    this.storeRawResponse = storeRawResponse;
  }

  public Map<String, String> getQueryParameters() {
    return new LinkedHashMap<>();
  }

  public Map<String, String> getRestParameters() {
    return new HashMap<>();
  }

}
