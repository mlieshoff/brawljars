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
package brawljars.api.intern;

import brawljars.api.Api;
import brawljars.api.intern.brawlers.BrawlerApi;
import brawljars.api.intern.clubs.ClubApi;
import brawljars.api.intern.events.EventApi;
import brawljars.api.intern.players.PlayerApi;
import brawljars.api.intern.rankings.RankingApi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultApiClasses {

    private final Map<Class<? extends Api>, String> apiClassMap =
            Collections.unmodifiableMap(
                    new HashMap<Class<? extends Api>, String>() {
                        {
                            put(PlayerApi.class, "brawljars.api.intern.players.PlayerApiImpl");
                            put(ClubApi.class, "brawljars.api.intern.clubs.ClubApiImpl");
                            put(RankingApi.class, "brawljars.api.intern.rankings.RankingApiImpl");
                            put(BrawlerApi.class, "brawljars.api.intern.brawlers.BrawlerApiImpl");
                            put(EventApi.class, "brawljars.api.intern.events.EventApiImpl");
                        }
                    });

    public Map<Class<? extends Api>, String> getApiClassMap() {
        return apiClassMap;
    }
}
