package brawljars.api.intern.brawlers;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;

class BrawlerApiImpl extends BaseApi implements BrawlerApi {

  BrawlerApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<BrawlersResponse> findAll(BrawlersRequest brawlersRequest) {
    return get("/brawlers", brawlersRequest, BrawlersResponse.class);
  }

  public Future<BrawlerResponse> findById(BrawlerRequest brawlerRequest) {
    return get("/brawlers/{brawlerId}", brawlerRequest, BrawlerResponse.class);
  }

}