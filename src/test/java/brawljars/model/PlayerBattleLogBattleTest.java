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
class PlayerBattleLogBattleTest {

  private PlayerBattleLogBattle unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new PlayerBattleLogBattle();
  }

  @Test
  void setDuration_whenWithValidParameter_thenSetDuration() {
    int expected = 815;
    unitUnderTest.setDuration(expected);

    assertEquals(expected, unitUnderTest.getDuration());
  }

  @Test
  void setMode_whenWithValidParameter_thenSetMode() {
    String expected = "astring";
    unitUnderTest.setMode(expected);

    assertEquals(expected, unitUnderTest.getMode());
  }

  @Test
  void setResult_whenWithValidParameter_thenSetResult() {
    String expected = "astring";
    unitUnderTest.setResult(expected);

    assertEquals(expected, unitUnderTest.getResult());
  }

  @Test
  void setStarPlayer_whenWithValidParameter_thenSetStarPlayer() {
    PlayerBattleLogBattleStarPlayer expected = new PlayerBattleLogBattleStarPlayer();
    unitUnderTest.setStarPlayer(expected);

    assertEquals(expected, unitUnderTest.getStarPlayer());
  }

  @Test
  void setTeams_whenWithValidParameter_thenSetTeams() {
    List<PlayerBattleLogBattleTeam> expected = new ArrayList<PlayerBattleLogBattleTeam>();
    unitUnderTest.setTeams(expected);

    assertEquals(expected, unitUnderTest.getTeams());
  }

  @Test
  void setType_whenWithValidParameter_thenSetType() {
    String expected = "astring";
    unitUnderTest.setType(expected);

    assertEquals(expected, unitUnderTest.getType());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setDuration(815);
    unitUnderTest.setMode("astring");
    unitUnderTest.setResult("astring");
    unitUnderTest.setStarPlayer(new PlayerBattleLogBattleStarPlayer());
    unitUnderTest.setTeams(new ArrayList<PlayerBattleLogBattleTeam>());
    unitUnderTest.setType("astring");
    String
        expected =
        "PlayerBattleLogBattle(duration=815, mode=astring, result=astring, starPlayer="
            + new PlayerBattleLogBattleStarPlayer() + ", teams=" + new ArrayList<PlayerBattleLogBattleTeam>()
            + ", type=astring)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}