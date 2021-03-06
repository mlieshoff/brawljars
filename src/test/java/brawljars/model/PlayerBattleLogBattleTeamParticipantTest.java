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
class PlayerBattleLogBattleTeamParticipantTest {

  private PlayerBattleLogBattleTeamParticipant unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new PlayerBattleLogBattleTeamParticipant();
  }

  @Test
  void setBrawler_whenWithValidParameter_thenSetBrawler() {
    PlayerBattleLogBattleBrawler expected = new PlayerBattleLogBattleBrawler();
    unitUnderTest.setBrawler(expected);

    assertEquals(expected, unitUnderTest.getBrawler());
  }

  @Test
  void setName_whenWithValidParameter_thenSetName() {
    String expected = "astring";
    unitUnderTest.setName(expected);

    assertEquals(expected, unitUnderTest.getName());
  }

  @Test
  void setTag_whenWithValidParameter_thenSetTag() {
    String expected = "astring";
    unitUnderTest.setTag(expected);

    assertEquals(expected, unitUnderTest.getTag());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setBrawler(new PlayerBattleLogBattleBrawler());
    unitUnderTest.setName("astring");
    unitUnderTest.setTag("astring");
    String
        expected =
        "PlayerBattleLogBattleTeamParticipant(brawler=" + new PlayerBattleLogBattleBrawler()
            + ", name=astring, tag=astring)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}