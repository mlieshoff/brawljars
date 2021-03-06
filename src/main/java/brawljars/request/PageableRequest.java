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
package brawljars.request;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Map;
import brawljars.response.Callback;
import brawljars.response.IResponse;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Michael Lieshoff
 */
@Getter
public class PageableRequest<T extends IResponse> extends Request<T> {

  static final String QUERY_PARAM_LIMIT = "limit";
  static final String QUERY_PARAM_AFTER = "after";
  static final String QUERY_PARAM_BEFORE = "before";

  private final int limit;

  private final String after;
  private final String before;

  @Builder(builderMethodName = "pageableRequestBuilder")
  PageableRequest(Callback<T> callback, int limit, String after, String before) {
    super(callback);
    this.limit = limit;
    this.after = after;
    this.before = before;
  }

  public Map<String, String> getQueryParameters() {
    Map<String, String> map = super.getQueryParameters();
    if (limit > 0) {
      map.put(QUERY_PARAM_LIMIT, String.valueOf(limit));
    }
    if (isNotBlank(after)) {
      map.put(QUERY_PARAM_AFTER, after);
    }
    if (isNotBlank(before)) {
      map.put(QUERY_PARAM_BEFORE, before);
    }
    return map;
  }

}
