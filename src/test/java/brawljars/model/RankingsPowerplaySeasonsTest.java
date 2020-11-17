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
class RankingsPowerplaySeasonsTest {

  private RankingsPowerplaySeasons unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new RankingsPowerplaySeasons();
  }

  @Test
  void setEndTime_whenWithValidParameter_thenSetEndTime() {
    String expected = "astring";
    unitUnderTest.setEndTime(expected);

    assertEquals(expected, unitUnderTest.getEndTime());
  }

  @Test
  void setId_whenWithValidParameter_thenSetId() {
    String expected = "astring";
    unitUnderTest.setId(expected);

    assertEquals(expected, unitUnderTest.getId());
  }

  @Test
  void setStartTime_whenWithValidParameter_thenSetStartTime() {
    String expected = "astring";
    unitUnderTest.setStartTime(expected);

    assertEquals(expected, unitUnderTest.getStartTime());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setEndTime("astring");
    unitUnderTest.setId("astring");
    unitUnderTest.setStartTime("astring");
    String expected = "RankingsPowerplaySeasons(endTime=astring, id=astring, startTime=astring)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}