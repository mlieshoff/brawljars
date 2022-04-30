package brawljars.api.intern.event;

import java.util.Map;
import brawljars.common.Request;
import lombok.Builder;

public class EventRotationRequest extends Request<EventRotationResponse> {


  @Builder
  private EventRotationRequest(boolean storeRawResponse) {
    super(storeRawResponse);
  }

  public static EventRotationRequestBuilder builder() {
    return new EventRotationRequestBuilder()
    ;
  }

  @Override
  public Map<String, String> getRestParameters() {
    Map<String, String> map = super.getRestParameters();
    return map;
  }

}