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
package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

import java.util.List;

@Data
public class Battle {

    @SerializedName("mode")
    private String mode;

    @SerializedName("type")
    private String type;

    @SerializedName("result")
    private String result;

    @SerializedName("duration")
    private Integer duration;

    @SerializedName("rank")
    private Integer rank;

    @SerializedName("trophyChange")
    private Integer trophyChange;

    @SerializedName("starPlayer")
    private StarPlayer starPlayer;

    @SerializedName("teams")
    private List<Team> teams;

    @SerializedName("players")
    private List<TeamPlayer> players;
}
