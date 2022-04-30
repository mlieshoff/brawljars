package brawljars.api;

import static brawljars.common.Utils.require;

import java.util.concurrent.Future;
import brawljars.common.IResponse;
import brawljars.common.Request;
import brawljars.connector.Connector;
import brawljars.connector.ConnectorException;
import brawljars.connector.RequestContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BaseApi implements Api {

  private final ExecutorServiceDecorator executorServiceDecorator = new ExecutorServiceDecorator();

  @NonNull
  private final ApiContext apiContext;

  protected <T extends IResponse> Future<T> get(String part, Request<?> request,
                                                Class<T> responseClass) {
    require("part", part);
    require("request", request);
    require("responseClass", responseClass);
    String apiKey = apiContext.getApiKey();
    Connector connector = apiContext.getConnector();
    try {
      String url = apiContext.getUrl() + part;
      RequestContext requestContext = new RequestContext(url, apiKey, request, responseClass);
      return executorServiceDecorator.submit(connector, requestContext);
    } catch (ConnectorException e) {
      throw new ApiException(e);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

}
