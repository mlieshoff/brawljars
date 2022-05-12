package brawljars.api.intern.brawlers;

import java.io.IOException;
import java.util.concurrent.Future;
import brawljars.api.Api;

public interface BrawlerApi extends Api {

  Future<brawljars.api.intern.brawlers.BrawlersResponse> findAll(brawljars.api.intern.brawlers.BrawlersRequest brawlersRequest);
  Future<brawljars.api.intern.brawlers.BrawlerResponse> findById(brawljars.api.intern.brawlers.BrawlerRequest brawlerRequest);

}
