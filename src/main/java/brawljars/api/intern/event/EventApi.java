package brawljars.api.intern.event;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface EventApi extends Api {

  brawljars.api.intern.event.EventRotationResponse findEventRotation(brawljars.api.intern.event.EventRotationRequest eventRotationRequest) throws ApiException;

}
