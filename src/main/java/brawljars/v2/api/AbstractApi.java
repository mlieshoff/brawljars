package brawljars.v2.api;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.apache.commons.collections4.MapUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import brawljars.v2.api.common.Request;

public abstract class AbstractApi implements Api {

  private final ApiContext apiContext;

  public AbstractApi(ApiContext apiContext) {
    this.apiContext = apiContext;
  }

  @Override
  public ApiContext getApiContext() {
    return apiContext;
  }

  protected String createUrl(String part, Request request) throws UnsupportedEncodingException {
    return appendToUrl(getApiContext().getUrl() + part, request.getQueryParameters());
  }

  private String appendToUrl(String url, Map<String, String> parameters) throws UnsupportedEncodingException {
    StringBuilder appendedUrl = new StringBuilder(url);
    if (MapUtils.isNotEmpty(parameters)) {
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
    /*
    if (CollectionUtils.isNotEmpty(restUrlParts)) {
      Object[] encodedParams = restUrlParts.stream().map(s -> {
        try {
          return encode(s);
        } catch (UnsupportedEncodingException e) {
          throw new IllegalStateException(e);
        }
      }).toArray();
      result = String.format(result, encodedParams);
    }
    */
//    log.info("request to: {}", result);
    return result;
  }

  String encode(String s) throws UnsupportedEncodingException {
    return URLEncoder.encode(s, "UTF-8");
  }

}
