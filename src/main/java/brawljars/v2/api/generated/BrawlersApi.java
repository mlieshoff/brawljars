package brawljars.v2.api.generated;

import java.io.IOException;
import brawljars.v2.api.Api;

public interface BrawlersApi extends Api {

  BrawlersResponse getBrawlers(BrawlersRequest brawlersRequest) throws IOException;

}
