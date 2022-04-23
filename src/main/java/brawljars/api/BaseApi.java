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
      String url = createUrl(part, request);
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

  private String createUrl(String part, Request<?> request) throws UnsupportedEncodingException {
    return appendToUrl(apiContext.getUrl() + part, request.getQueryParameters(), request.getRestParameters());
  }

  private String appendToUrl(String url, Map<String, String> parameters, Map<String, String> restUrlParts)
      throws UnsupportedEncodingException {
    StringBuilder appendedUrl = new StringBuilder(url);
    if (isNotEmpty(parameters)) {
      appendedUrl.append('?');
      for (Iterator<Entry<String, String>> iterator = parameters.entrySet().iterator(); iterator.hasNext(); ) {
        Entry<String, String> entry = iterator.next();
        String name = entry.getKey();
        String value = entry.getKey();
        appendedUrl.append(name);
        appendedUrl.append('=');
        if (isNotBlank(value)) {
          appendedUrl.append(encode(entry.getValue()));
        }
        if (iterator.hasNext()) {
          appendedUrl.append('&');
        }
      }
    }
    String result = appendedUrl.toString();
    if (isNotEmpty(restUrlParts)) {
      for (Entry<String, String> entry : restUrlParts.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        result = result.replace("{" + key + "}", encode(value));
      }
    }
    log.info("request to: {}", result);
    return result;
  }

  private String encode(String s) throws UnsupportedEncodingException {
    return URLEncoder.encode(s, "UTF-8");
  }

  @Override
  public RawResponse getLastResponse() {
    return apiContext.getConnector().getLastRawResponse();
  }

}
