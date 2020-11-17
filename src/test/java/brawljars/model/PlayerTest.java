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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.mili.generator")
class PlayerTest {

  private Player unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new Player();
  }

  @Test
  void setBestRoboRumbleTime_whenWithValidParameter_thenSetBestRoboRumbleTime() {
    int expected = 815;
    unitUnderTest.setBestRoboRumbleTime(expected);

    assertEquals(expected, unitUnderTest.getBestRoboRumbleTime());
  }

  @Test
  void setBestTimeAsBigBrawler_whenWithValidParameter_thenSetBestTimeAsBigBrawler() {
    int expected = 815;
    unitUnderTest.setBestTimeAsBigBrawler(expected);

    assertEquals(expected, unitUnderTest.getBestTimeAsBigBrawler());
  }

  @Test
  void setBrawlers_whenWithValidParameter_thenSetBrawlers() {
    List<PlayerBrawler> expected = new ArrayList<PlayerBrawler>();
    unitUnderTest.setBrawlers(expected);

    assertEquals(expected, unitUnderTest.getBrawlers());
  }

  @Test
  void setClub_whenWithValidParameter_thenSetClub() {
    PlayerClub expected = new PlayerClub();
    unitUnderTest.setClub(expected);

    assertEquals(expected, unitUnderTest.getClub());
  }

  @Test
  void setDuoVictories_whenWithValidParameter_thenSetDuoVictories() {
    int expected = 815;
    unitUnderTest.setDuoVictories(expected);

    assertEquals(expected, unitUnderTest.getDuoVictories());
  }

  @Test
  void setExpLevel_whenWithValidParameter_thenSetExpLevel() {
    int expected = 815;
    unitUnderTest.setExpLevel(expected);

    assertEquals(expected, unitUnderTest.getExpLevel());
  }

  @Test
  void setExpPoints_whenWithValidParameter_thenSetExpPoints() {
    int expected = 815;
    unitUnderTest.setExpPoints(expected);

    assertEquals(expected, unitUnderTest.getExpPoints());
  }

  @Test
  void setHighestTrophies_whenWithValidParameter_thenSetHighestTrophies() {
    int expected = 815;
    unitUnderTest.setHighestTrophies(expected);

    assertEquals(expected, unitUnderTest.getHighestTrophies());
  }

  @Test
  void setIcon_whenWithValidParameter_thenSetIcon() {
    PlayerIcon expected = new PlayerIcon();
    unitUnderTest.setIcon(expected);

    assertEquals(expected, unitUnderTest.getIcon());
  }

  @Test
  void setQualifiedFromChampionshipChallenge_whenWithValidParameter_thenSetQualifiedFromChampionshipChallenge() {
    boolean expected = false;
    unitUnderTest.setQualifiedFromChampionshipChallenge(expected);

    assertEquals(expected, unitUnderTest.isQualifiedFromChampionshipChallenge());
  }

  @Test
  void setName_whenWithValidParameter_thenSetName() {
    String expected = "astring";
    unitUnderTest.setName(expected);

    assertEquals(expected, unitUnderTest.getName());
  }

  @Test
  void setSoloVictories_whenWithValidParameter_thenSetSoloVictories() {
    int expected = 815;
    unitUnderTest.setSoloVictories(expected);

    assertEquals(expected, unitUnderTest.getSoloVictories());
  }

  @Test
  void setTag_whenWithValidParameter_thenSetTag() {
    String expected = "astring";
    unitUnderTest.setTag(expected);

    assertEquals(expected, unitUnderTest.getTag());
  }

  @Test
  void setThreeVsThreeVictories_whenWithValidParameter_thenSetThreeVsThreeVictories() {
    int expected = 815;
    unitUnderTest.setThreeVsThreeVictories(expected);

    assertEquals(expected, unitUnderTest.getThreeVsThreeVictories());
  }

  @Test
  void setTrophies_whenWithValidParameter_thenSetTrophies() {
    int expected = 815;
    unitUnderTest.setTrophies(expected);

    assertEquals(expected, unitUnderTest.getTrophies());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setBestRoboRumbleTime(815);
    unitUnderTest.setBestTimeAsBigBrawler(815);
    unitUnderTest.setBrawlers(new ArrayList<PlayerBrawler>());
    unitUnderTest.setClub(new PlayerClub());
    unitUnderTest.setDuoVictories(815);
    unitUnderTest.setExpLevel(815);
    unitUnderTest.setExpPoints(815);
    unitUnderTest.setHighestTrophies(815);
    unitUnderTest.setIcon(new PlayerIcon());
    unitUnderTest.setQualifiedFromChampionshipChallenge(false);
    unitUnderTest.setName("astring");
    unitUnderTest.setSoloVictories(815);
    unitUnderTest.setTag("astring");
    unitUnderTest.setThreeVsThreeVictories(815);
    unitUnderTest.setTrophies(815);
    String
        expected =
        "Player(bestRoboRumbleTime=815, bestTimeAsBigBrawler=815, brawlers=" + new ArrayList<PlayerBrawler>()
            + ", club=" + new PlayerClub()
            + ", duoVictories=815, expLevel=815, expPoints=815, highestTrophies=815, icon=" + new PlayerIcon()
            + ", QualifiedFromChampionshipChallenge=false, name=astring, soloVictories=815, tag=astring, threeVsThreeVictories=815, trophies=815)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}