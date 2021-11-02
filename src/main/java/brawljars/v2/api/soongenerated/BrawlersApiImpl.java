package brawljars.v2.api.soongenerated;

import java.io.IOException;
import brawljars.v2.api.AbstractApi;
import brawljars.v2.api.ApiContext;
import brawljars.v2.api.RequestContext;

class BrawlersApiImpl extends AbstractApi implements BrawlersApi {

  public BrawlersApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public BrawlersResponse getBrawlers(BrawlersRequest brawlersRequest) throws IOException {
    return getApiContext().getConnector()
        .get(new RequestContext(getApiContext().getUrl() + "/brawlers", getApiContext().getApiKey()));
  }

}