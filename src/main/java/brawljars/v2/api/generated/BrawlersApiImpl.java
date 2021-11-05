package brawljars.v2.api.generated;

import java.io.IOException;
import brawljars.v2.api.AbstractApi;
import brawljars.v2.api.ApiContext;
import brawljars.v2.api.Connector;
import brawljars.v2.api.RequestContext;

class BrawlersApiImpl extends AbstractApi implements BrawlersApi {

  public BrawlersApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public BrawlersResponse findAll(BrawlersRequest brawlersRequest) throws IOException {
    String apiKey = getApiContext().getApiKey();
    Connector connector = getApiContext().getConnector();
    String url = createUrl("/brawlers", brawlersRequest);
    RequestContext requestContext = new RequestContext(url, apiKey, brawlersRequest, BrawlersResponse.class);
    return connector.get(requestContext);
  }

  public BrawlerResponse findById(BrawlerRequest brawlerRequest) throws IOException {
    String apiKey = getApiContext().getApiKey();
    Connector connector = getApiContext().getConnector();
    String url = createUrl("/brawlers/{brawlerId}", brawlerRequest);
    RequestContext requestContext = new RequestContext(url, apiKey, brawlerRequest, BrawlerResponse.class);
    return connector.get(requestContext);
  }

}