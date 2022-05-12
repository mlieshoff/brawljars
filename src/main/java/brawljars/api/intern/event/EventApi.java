package brawljars.api.intern.event;

import java.io.IOException;
import java.util.concurrent.Future;
import brawljars.api.Api;

public interface EventApi extends Api {

  Future<brawljars.api.intern.event.EventRotationResponse> findEventRotation(brawljars.api.intern.event.EventRotationRequest eventRotationRequest);

}
