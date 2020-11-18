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
import brawljars.request.GetBrawlersRequest;
import brawljars.request.GetClubMembersRequest;
import brawljars.request.GetClubRequest;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.request.GetRankingsBrawlerRequest;
import brawljars.request.GetRankingsClubsRequest;
import brawljars.request.GetRankingsPlayersRequest;
import brawljars.request.GetRankingsPowerplaySeasonsRequest;
import brawljars.request.GetRankingsPowerplaySeasonsSeasonRequest;
import brawljars.request.Request;
import brawljars.response.GetBrawlersResponse;
import brawljars.response.GetClubMembersResponse;
import brawljars.response.GetClubResponse;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;
import brawljars.response.GetRankingsBrawlerResponse;
import brawljars.response.GetRankingsClubsResponse;
import brawljars.response.GetRankingsPlayersResponse;
import brawljars.response.GetRankingsPowerplaySeasonsResponse;
import brawljars.response.GetRankingsPowerplaySeasonsSeasonResponse;
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

  private static Map<String, String> createAuthHeader(String apiKey) {
    String headerValue = "Bearer " + apiKey;
    return ImmutableMap.<String, String>builder().put(AUTHORIZATION, headerValue).build();
  }

  RawResponse getLastRawResponse() {
    return createCrawler().getLastRawResponse();
  }

  GetPlayerResponse getPlayer(GetPlayerRequest getPlayerRequest) {
    return executeRequest("players/%s", getPlayerRequest, GetPlayerResponse.class);
  }

  private <T extends Request<R>, R extends IResponse> R executeRequest(String module, T request,
                                                                       Class<R> responseClass) {
    try {
      return singleObjectFromJson(module, request, responseClass);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private <T extends Request<R>, R extends IResponse> R singleObjectFromJson(String part, T request,
                                                                             Class<R> responseClass)
      throws IOException {
    checkNotNull(request, request.getClass().getSimpleName());
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

  private <T extends Request<R>, R extends IResponse> R toJson(String part, T request, Class<R> responseClass)
      throws IOException {
    String json = get(createUrl(part), request);
    return new Gson().fromJson(json, responseClass);
  }

  private <T extends Request<R>, R extends IResponse> String get(String url, T request) throws IOException {
    return createCrawler()
        .get(url, createAuthHeader(apiKey), request.getQueryParameters(), request.getRestParameters());
  }

  private Crawler createCrawler() {
    return crawlerFactory.createCrawler();
  }

  private String createUrl(String part) {
    return url + part;
  }

  GetPlayerBattleLogResponse getPlayerBattleLog(GetPlayerBattleLogRequest getPlayerBattleLogRequest) {
    return executeRequest("players/%s/battlelog", getPlayerBattleLogRequest, GetPlayerBattleLogResponse.class);
  }

  GetClubResponse getClub(GetClubRequest getClubRequest) {
    return executeRequest("clubs/%s", getClubRequest, GetClubResponse.class);
  }

  GetClubMembersResponse getClubMembers(GetClubMembersRequest getClubMembersRequest) {
    return executeRequest("clubs/%s/members", getClubMembersRequest, GetClubMembersResponse.class);
  }

  GetRankingsPowerplaySeasonsResponse getRankingsPowerplaySeasons(
      GetRankingsPowerplaySeasonsRequest getRankingsPowerplaySeasonsRequest) {
    return executeRequest("rankings/%s/powerplay/seasons", getRankingsPowerplaySeasonsRequest,
        GetRankingsPowerplaySeasonsResponse.class);
  }

  GetRankingsPowerplaySeasonsSeasonResponse getRankingsPowerplaySeasonsSeason(
      GetRankingsPowerplaySeasonsSeasonRequest getRankingsPowerplaySeasonsSeasonRequest) {
    return executeRequest("rankings/%s/powerplay/seasons/%s", getRankingsPowerplaySeasonsSeasonRequest,
        GetRankingsPowerplaySeasonsSeasonResponse.class);
  }

  GetRankingsClubsResponse getRankingsClubs(GetRankingsClubsRequest getRankingsClubsRequest) {
    return executeRequest("rankings/%s/clubs", getRankingsClubsRequest, GetRankingsClubsResponse.class);
  }

  GetRankingsBrawlerResponse getRankingsBrawler(GetRankingsBrawlerRequest getRankingsBrawlerRequest) {
    return executeRequest("rankings/%s/brawler/%s", getRankingsBrawlerRequest, GetRankingsBrawlerResponse.class);
  }

  GetRankingsPlayersResponse getRankingsPlayers(GetRankingsPlayersRequest getRankingsPlayersRequest) {
    return executeRequest("rankings/%s/players", getRankingsPlayersRequest, GetRankingsPlayersResponse.class);
  }

  GetBrawlersResponse getBrawlers(GetBrawlersRequest getBrawlersRequest) {
    return executeRequest("brawlers", getBrawlersRequest, GetBrawlersResponse.class);
  }

}
