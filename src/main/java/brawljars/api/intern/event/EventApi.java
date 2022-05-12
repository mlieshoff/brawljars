package brawljars.api.intern.event;

import java.util.concurrent.Future;
import brawljars.api.Api;

public interface EventApi extends Api {

  Future<EventRotationResponse> findEventRotation(EventRotationRequest eventRotationRequest);

}
