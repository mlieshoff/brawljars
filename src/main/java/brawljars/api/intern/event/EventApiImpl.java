package brawljars.api.intern.event;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;

class EventApiImpl extends BaseApi implements EventApi {

  EventApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<EventRotationResponse> findEventRotation(EventRotationRequest eventRotationRequest) {
    return get("/events/rotation", eventRotationRequest, EventRotationResponse.class);
  }

}