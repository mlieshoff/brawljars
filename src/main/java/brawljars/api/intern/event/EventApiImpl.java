package brawljars.api.intern.event;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;

class EventApiImpl extends BaseApi implements EventApi {

  public EventApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<brawljars.api.intern.event.EventRotationResponse> findEventRotation(brawljars.api.intern.event.EventRotationRequest eventRotationRequest) {
    return get("/events/rotation", eventRotationRequest, brawljars.api.intern.event.EventRotationResponse.class);
  }

}