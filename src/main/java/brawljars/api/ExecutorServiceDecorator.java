package brawljars.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import brawljars.common.IResponse;
import brawljars.connector.Connector;
import brawljars.connector.RequestContext;

class ExecutorServiceDecorator {

  private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(8);

  public <T extends IResponse> Future<T> submit(Connector connector, RequestContext requestContext) {
    return EXECUTOR_SERVICE.submit(() -> connector.get(requestContext));
  }

}
