package brawljars.api.intern.brawlers;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface BrawlersApi extends Api {

  BrawlersResponse findAll(BrawlersRequest brawlersRequest) throws ApiException;
  BrawlerResponse findById(BrawlerRequest brawlerRequest) throws ApiException;

}
