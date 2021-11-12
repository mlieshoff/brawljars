package brawljars.api.intern.brawlers;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class BrawlersApiImpl extends BaseApi implements BrawlersApi {

  public BrawlersApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public BrawlersResponse findAll(BrawlersRequest brawlersRequest) throws ApiException {
    return get("/brawlers", brawlersRequest, BrawlersResponse.class);
  }

  public BrawlerResponse findById(BrawlerRequest brawlerRequest) throws ApiException {
    return get("/brawlers/{brawlerId}", brawlerRequest, BrawlerResponse.class);
  }

}