package brawljars.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import brawljars.common.Request;
import brawljars.connector.Connector;
import brawljars.connector.RequestContext;

class ExecutorServiceDecorator {

  private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(8);

  void submit(Request request, Connector connector, RequestContext requestContext) {
    EXECUTOR_SERVICE.submit(() -> {
      try {
        request.getCallback().onResponse(connector.get(requestContext));
      } catch (Exception e) {
        request.getCallback().onException(e);
      }
    });
  }

}
