package brawljars.api.intern.brawlers;

import java.util.concurrent.Future;
import brawljars.api.Api;

public interface BrawlerApi extends Api {

  Future<BrawlersResponse> findAll(BrawlersRequest brawlersRequest);
  Future<BrawlerResponse> findById(BrawlerRequest brawlerRequest);

}
