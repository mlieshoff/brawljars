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
package brawljars.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import javax.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Generated("org.mili.generator")
@Data
@EqualsAndHashCode
@ToString
public class Player {

  @SerializedName("bestRoboRumbleTime")
  private int bestRoboRumbleTime;

  @SerializedName("bestTimeAsBigBrawler")
  private int bestTimeAsBigBrawler;

  @SerializedName("brawlers")
  private List<PlayerBrawler> brawlers;

  @SerializedName("club")
  private PlayerClub club;

  @SerializedName("duoVictories")
  private int duoVictories;

  @SerializedName("expLevel")
  private int expLevel;

  @SerializedName("expPoints")
  private int expPoints;

  @SerializedName("highestTrophies")
  private int highestTrophies;

  @SerializedName("icon")
  private PlayerIcon icon;

  @SerializedName("isQualifiedFromChampionshipChallenge")
  private boolean QualifiedFromChampionshipChallenge;

  @SerializedName("name")
  private String name;

  @SerializedName("soloVictories")
  private int soloVictories;

  @SerializedName("tag")
  private String tag;

  @SerializedName("3vs3Victories")
  private int threeVsThreeVictories;

  @SerializedName("trophies")
  private int trophies;

}