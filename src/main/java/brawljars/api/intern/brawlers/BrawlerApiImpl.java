package brawljars.api.intern.brawlers;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;

class BrawlerApiImpl extends BaseApi implements BrawlerApi {

  public BrawlerApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<brawljars.api.intern.brawlers.BrawlersResponse> findAll(brawljars.api.intern.brawlers.BrawlersRequest brawlersRequest) {
    return get("/brawlers", brawlersRequest, brawljars.api.intern.brawlers.BrawlersResponse.class);
  }

  public Future<brawljars.api.intern.brawlers.BrawlerResponse> findById(brawljars.api.intern.brawlers.BrawlerRequest brawlerRequest) {
    return get("/brawlers/{brawlerId}", brawlerRequest, brawljars.api.intern.brawlers.BrawlerResponse.class);
  }

}