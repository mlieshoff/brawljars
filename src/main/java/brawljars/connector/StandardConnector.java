/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package brawljars.connector;

import static brawljars.common.Utils.isNotBlank;
import static brawljars.common.Utils.isNotEmpty;
import static brawljars.common.Utils.require;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.HttpStatus.SC_OK;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import brawljars.common.RawResponse;
import brawljars.common.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StandardConnector implements Connector {

  private static final ThreadLocal<RawResponse> RESPONSE = new ThreadLocal<>();

  private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

  @Override
  public <T> T get(RequestContext requestContext) throws ConnectorException {
    require("requestContext", requestContext);
    try {
      String url = requestContext.getUrl();
      String
          replacedUrl =
          appendToUrl(url, requestContext.getRequest().getQueryParameters(),
              requestContext.getRequest().getRestParameters());
      HttpClient client = HttpClientBuilder.create().build();
      ;
      HttpGet request = createRequest(replacedUrl, requestContext.getApiKey());
      HttpResponse response = client.execute(request);
      logResponse(response);
      StatusLine statusLine = response.getStatusLine();
      if (statusLine.getStatusCode() != SC_OK) {
        setLastResponse(null, response);
        throw new ConnectorException(statusLine.toString());
      }
      StringBuilder content = new StringBuilder();
      try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), UTF_8))) {
        String line;
        while ((line = rd.readLine()) != null) {
          content.append(line);
        }
      }
      String json = content.toString();
      log.info("    response content: {}", json);
      setLastResponse(json, response);
      return (T) GSON.fromJson(json, requestContext.getResponseClass());
    } catch (IOException e) {
      throw new ConnectorException(e);
    }
  }

  private static void setLastResponse(String result, HttpResponse response) {
    RawResponse rawResponse = new RawResponse();
    rawResponse.setRaw(result);
    if (isNotEmpty(response.getAllHeaders())) {
      rawResponse.getResponseHeaders().clear();
      for (Header header : response.getAllHeaders()) {
        rawResponse.getResponseHeaders().put(header.getName().toLowerCase(), header.getValue());
      }
    }
    RESPONSE.set(rawResponse);
  }

  private static void logResponse(HttpResponse httpResponse) {
    if (log.isInfoEnabled()) {
      for (Header header : httpResponse.getAllHeaders()) {
        log.info("    response header: {}={}", header.getName(), header.getValue());
      }
      StatusLine statusLine = httpResponse.getStatusLine();
      log.info("    status code: {}- {}", statusLine.getStatusCode(), statusLine.getReasonPhrase());
    }
  }

  private String appendToUrl(String url, Map<String, String> parameters, Map<String, String> restParameters)
      throws UnsupportedEncodingException {
    StringBuilder appendedUrl = new StringBuilder(url);
    if (isNotEmpty(parameters)) {
      appendedUrl.append('?');
      for (Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator(); iterator.hasNext(); ) {
        Map.Entry<String, String> entry = iterator.next();
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
    if (isNotEmpty(restParameters)) {
      for (Map.Entry<String, String> entry : restParameters.entrySet()) {
        String encodedValue = encode(entry.getValue());
        result.replace("{" + entry.getKey() + "}", encodedValue);
      }
    }
    log.info("request to: {}", result);
    return result;
  }

  String encode(String s) throws UnsupportedEncodingException {
    return URLEncoder.encode(s, "UTF-8");
  }

  private static HttpGet createRequest(String url, String apiKey) {
    HttpGet httpGet = new HttpGet(url);
    httpGet.addHeader("Authorization", "Bearer " + apiKey);
    return httpGet;
  }

  @Override
  public RawResponse getLastRawResponse() {
    return RESPONSE.get();
  }

}
