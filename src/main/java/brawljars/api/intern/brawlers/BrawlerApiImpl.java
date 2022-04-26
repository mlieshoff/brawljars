package brawljars.api.intern.brawlers;

import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class BrawlerApiImpl extends BaseApi implements BrawlerApi {

  public BrawlerApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public brawljars.api.intern.brawlers.BrawlersResponse findAll(brawljars.api.intern.brawlers.BrawlersRequest brawlersRequest) throws ApiException {
    return get("/brawlers", brawlersRequest, brawljars.api.intern.brawlers.BrawlersResponse.class);
  }

  public brawljars.api.intern.brawlers.BrawlerResponse findById(brawljars.api.intern.brawlers.BrawlerRequest brawlerRequest) throws ApiException {
    return get("/brawlers/{brawlerId}", brawlerRequest, brawljars.api.intern.brawlers.BrawlerResponse.class);
  }

}