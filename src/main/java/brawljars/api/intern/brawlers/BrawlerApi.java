package brawljars.api.intern.brawlers;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface BrawlerApi extends Api {

  brawljars.api.intern.brawlers.BrawlersResponse findAll(brawljars.api.intern.brawlers.BrawlersRequest brawlersRequest) throws ApiException;
  brawljars.api.intern.brawlers.BrawlerResponse findById(brawljars.api.intern.brawlers.BrawlerRequest brawlerRequest) throws ApiException;

}
