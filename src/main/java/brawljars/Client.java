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
package brawljars;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;
import static java.lang.Thread.NORM_PRIORITY;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.gson.Gson;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.util.Map;
import brawljars.request.GetPlayerRequest;
import brawljars.request.Request;
import brawljars.response.GetPlayerResponse;
import brawljars.response.IResponse;
import brawljars.response.RawResponse;

/**
 * @author Michael Lieshoff
 */
public class Client {

  private static final ListeningExecutorService EXECUTOR_SERVICE = listeningDecorator(
      newFixedThreadPool(8, new BasicThreadFactory.Builder()
          .daemon(true)
          .namingPattern("brawljars.async")
          .priority(NORM_PRIORITY)
          .build())
  );

  private final String url;
  private final String apiKey;

  private final CrawlerFactory crawlerFactory;

  Client(String url, String apiKey, CrawlerFactory crawlerFactory) {
    checkString(url);
    checkNotNull(crawlerFactory);
    this.url = url;
    this.apiKey = apiKey;
    this.crawlerFactory = crawlerFactory;
  }

  private static void checkString(String url) {
    checkNotNull(url);
    checkArgument(!url.isEmpty(), url);
  }

  GetPlayerResponse getPlayer(GetPlayerRequest getPlayerRequest) throws IOException {
    return singleObjectFromJson("getPlayerRequest", "players/%s", getPlayerRequest, GetPlayerResponse.class);
  }

  private <T extends IResponse> T singleObjectFromJson(String nameOfRequest, String part, Request request,
                                                       Class<T> responseClass) throws IOException {
    checkNotNull(request, nameOfRequest);
    if (request.getCallback() == null) {
      return toJson(part, request, responseClass);
    } else {
      EXECUTOR_SERVICE.submit(() -> {
        try {
          request.getCallback().onResponse(toJson(part, request, responseClass));
        } catch (Exception e) {
          request.getCallback().onException(e);
        }
      });
      return null;
    }
  }

  private <T extends IResponse> T toJson(String part, Request request, Class<T> responseClass) throws IOException {
    String json = get(createUrl(part), request);
    return new Gson().fromJson(json, responseClass);
  }

  private String createUrl(String part) {
    return url + part;
  }

  private String get(String url, Request request) throws IOException {
    return createCrawler()
        .get(url, createAuthHeader(apiKey), request.getQueryParameters(), request.getRestParameters());
  }

  private Crawler createCrawler() {
    return crawlerFactory.createCrawler();
  }

  private static Map<String, String> createAuthHeader(String apiKey) {
    String headerValue = "Bearer " + apiKey;
    return ImmutableMap.<String, String>builder().put(AUTHORIZATION, headerValue).build();
  }

  RawResponse getLastRawResponse() {
    return createCrawler().getLastRawResponse();
  }

}
