package brawljars.api;

import static brawljars.common.Utils.isNotBlank;
import static brawljars.common.Utils.isNotEmpty;
import static brawljars.common.Utils.require;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import brawljars.common.IResponse;
import brawljars.common.RawResponse;
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

  protected <T extends IResponse> T get(String part, Request<?> request, Class<? extends IResponse> responseClass) {
    require("part", part);
    require("request", request);
    require("responseClass", responseClass);
    String apiKey = apiContext.getApiKey();
    Connector connector = apiContext.getConnector();
    try {
      String url = apiContext.getUrl() + part;
      RequestContext requestContext = new RequestContext(url, apiKey, request, responseClass);
      if (request.getCallback() == null) {
        return connector.get(requestContext);
      }
      executorServiceDecorator.submit(request, connector, requestContext);
      return null;
    } catch (ConnectorException e) {
      throw new ApiException(e);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public RawResponse getLastResponse() {
    return apiContext.getConnector().getLastRawResponse();
  }

}
