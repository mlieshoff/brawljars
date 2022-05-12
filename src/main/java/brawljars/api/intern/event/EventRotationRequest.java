package brawljars.api.intern.event;

import brawljars.common.Request;
import lombok.Builder;

public class EventRotationRequest extends Request {


  @Builder
  private EventRotationRequest(boolean storeRawResponse) {
    super(storeRawResponse);
  }

  public static EventRotationRequestBuilder builder() {
    return new EventRotationRequestBuilder()
    ;
  }


}