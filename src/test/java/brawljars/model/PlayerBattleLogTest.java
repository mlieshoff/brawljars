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

import javax.annotation.Generated;

@Generated("org.mili.generator")
class PlayerBattleLogTest {

  private PlayerBattleLog unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new PlayerBattleLog();
  }

  @Test
  void setBattle_whenWithValidParameter_thenSetBattle() {
    PlayerBattleLogBattle expected = new PlayerBattleLogBattle();
    unitUnderTest.setBattle(expected);

    assertEquals(expected, unitUnderTest.getBattle());
  }

  @Test
  void setBattleTime_whenWithValidParameter_thenSetBattleTime() {
    String expected = "astring";
    unitUnderTest.setBattleTime(expected);

    assertEquals(expected, unitUnderTest.getBattleTime());
  }

  @Test
  void setEvent_whenWithValidParameter_thenSetEvent() {
    PlayerBattleLogEvent expected = new PlayerBattleLogEvent();
    unitUnderTest.setEvent(expected);

    assertEquals(expected, unitUnderTest.getEvent());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setBattle(new PlayerBattleLogBattle());
    unitUnderTest.setBattleTime("astring");
    unitUnderTest.setEvent(new PlayerBattleLogEvent());
    String expected = "PlayerBattleLog(battle=" + new PlayerBattleLogBattle() + ", battleTime=astring, event=" + new PlayerBattleLogEvent() + ")";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}