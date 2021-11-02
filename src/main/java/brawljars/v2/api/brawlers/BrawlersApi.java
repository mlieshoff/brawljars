package brawljars.v2.api.brawlers;

import java.io.IOException;
import brawljars.v2.api.Api;
import brawljars.v2.api.brawlers.generated.BrawlersRequest;
import brawljars.v2.api.brawlers.generated.BrawlersResponse;

public interface BrawlersApi extends Api {

  BrawlersResponse getBrawlers(BrawlersRequest brawlersRequest) throws IOException;

}
