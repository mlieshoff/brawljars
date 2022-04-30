package brawljars.api.intern.brawlers;

import java.io.IOException;
import java.util.concurrent.Future;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface BrawlerApi extends Api {

  Future<brawljars.api.intern.brawlers.BrawlersResponse> findAll(brawljars.api.intern.brawlers.BrawlersRequest brawlersRequest) throws ApiException;
  Future<brawljars.api.intern.brawlers.BrawlerResponse> findById(brawljars.api.intern.brawlers.BrawlerRequest brawlerRequest) throws ApiException;

}
