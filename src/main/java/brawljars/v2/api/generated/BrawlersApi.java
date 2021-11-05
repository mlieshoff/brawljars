package brawljars.v2.api.generated;

import java.io.IOException;
import brawljars.v2.api.Api;

public interface BrawlersApi extends Api {

  BrawlersResponse findAll(BrawlersRequest brawlersRequest) throws IOException;
  BrawlerResponse findById(BrawlerRequest brawlerRequest) throws IOException;

}
