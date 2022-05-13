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
package brawljars.api.intern.players.player;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Player {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;
  @SerializedName("nameColor")
  private String nameColor;
  @SerializedName("icon")
  private Icon icon;
  @SerializedName("trophies")
  private int trophies;
  @SerializedName("highestTrophies")
  private int highestTrophies;
  @SerializedName("expLevel")
  private int expLevel;
  @SerializedName("expPoints")
  private int expPoints;
  @SerializedName("isQualifiedFromChampionshipChallenge")
  private boolean isQualifiedFromChampionshipChallenge;
  @SerializedName("3vs3Victories")
  private int threeVsThreeVictories;
  @SerializedName("soloVictories")
  private int soloVictories;
  @SerializedName("duoVictories")
  private int duoVictories;
  @SerializedName("bestRoboRumbleTime")
  private int bestRoboRumbleTime;
  @SerializedName("bestTimeAsBigBrawler")
  private int bestTimeAsBigBrawler;
  @SerializedName("club")
  private Club club;
  @SerializedName("brawlers")
  private List<Brawler> brawlers;

}