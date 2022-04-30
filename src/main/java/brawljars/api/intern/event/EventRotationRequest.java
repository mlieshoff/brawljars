package brawljars.api.intern.event;

import java.util.Map;
import brawljars.common.Request;
import brawljars.common.Callback;
import lombok.Builder;

public class EventRotationRequest extends Request<EventRotationResponse> {


  @Builder
  private EventRotationRequest(Callback<EventRotationResponse> callback, boolean storeRawResponse) {
    super(callback, storeRawResponse);
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