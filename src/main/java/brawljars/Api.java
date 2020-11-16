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

import java.util.function.Function;
import brawljars.request.GetPlayerBattleLogRequest;
import brawljars.request.GetPlayerRequest;
import brawljars.request.Request;
import brawljars.response.GetPlayerBattleLogResponse;
import brawljars.response.GetPlayerResponse;
import brawljars.response.IResponse;
import brawljars.response.RawResponse;

/**
 * @author Michael Lieshoff
 */
public class Api {

  private final ClientFactory clientFactory;

  private final String url;
  private final String apiKey;

  public Api(String url, String apiKey) {
    this(url, apiKey, new ClientFactory());
  }

  Api(String url, String apiKey, ClientFactory clientFactory) {
    checkString(url, "url");
    checkString(apiKey, "apiKey");
    this.url = url;
    this.apiKey = apiKey;
    this.clientFactory = clientFactory;
  }

  private static void checkString(String s, String key) {
    checkNotNull(s, key);
    checkArgument(!s.isEmpty(), key);
  }

  private Client createClient() {
    return clientFactory.createClient(url, apiKey);
  }

  public RawResponse getLastRawResponse() {
    return createClient().getLastRawResponse();
  }

  public GetPlayerResponse getPlayer(GetPlayerRequest getPlayerRequest) {
    return executeRequest(getPlayerRequest, client -> client.getPlayer(getPlayerRequest));
  }

  private <T extends Request<?>, R extends IResponse> R executeRequest(T request, Function<Client, R> function) {
    checkNotNull(request, "request");
    try {
      return function.apply(createClient());
    } catch (Exception e) {
      throw new ApiException(e);
    }
  }

  public GetPlayerBattleLogResponse getPlayerBattleLog(GetPlayerBattleLogRequest getPlayerBattleLogRequest) {
    return executeRequest(getPlayerBattleLogRequest, client -> client.getPlayerBattleLog(getPlayerBattleLogRequest));
  }

}