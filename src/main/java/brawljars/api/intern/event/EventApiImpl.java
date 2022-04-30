package brawljars.api.intern.event;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class EventApiImpl extends BaseApi implements EventApi {

  public EventApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public brawljars.api.intern.event.EventRotationResponse findEventRotation(brawljars.api.intern.event.EventRotationRequest eventRotationRequest) throws ApiException {
    return get("/events/rotation", eventRotationRequest, brawljars.api.intern.event.EventRotationResponse.class);
  }

}