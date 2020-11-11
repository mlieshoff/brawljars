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
class BrawlerTest {

  private Brawler unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new Brawler();
  }

  @Test
  void setGadgets_whenWithValidParameter_thenSetGadgets() {
    List<Gadget> expected = new ArrayList<Gadget>();
    unitUnderTest.setGadgets(expected);

    assertEquals(expected, unitUnderTest.getGadgets());
  }

  @Test
  void setHighestTrophies_whenWithValidParameter_thenSetHighestTrophies() {
    int expected = 815;
    unitUnderTest.setHighestTrophies(expected);

    assertEquals(expected, unitUnderTest.getHighestTrophies());
  }

  @Test
  void setId_whenWithValidParameter_thenSetId() {
    long expected = 4711L;
    unitUnderTest.setId(expected);

    assertEquals(expected, unitUnderTest.getId());
  }

  @Test
  void setName_whenWithValidParameter_thenSetName() {
    String expected = "astring";
    unitUnderTest.setName(expected);

    assertEquals(expected, unitUnderTest.getName());
  }

  @Test
  void setPower_whenWithValidParameter_thenSetPower() {
    int expected = 815;
    unitUnderTest.setPower(expected);

    assertEquals(expected, unitUnderTest.getPower());
  }

  @Test
  void setRank_whenWithValidParameter_thenSetRank() {
    int expected = 815;
    unitUnderTest.setRank(expected);

    assertEquals(expected, unitUnderTest.getRank());
  }

  @Test
  void setStarPowers_whenWithValidParameter_thenSetStarPowers() {
    List<StarPower> expected = new ArrayList<StarPower>();
    unitUnderTest.setStarPowers(expected);

    assertEquals(expected, unitUnderTest.getStarPowers());
  }

  @Test
  void setTrophies_whenWithValidParameter_thenSetTrophies() {
    int expected = 815;
    unitUnderTest.setTrophies(expected);

    assertEquals(expected, unitUnderTest.getTrophies());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setGadgets(new ArrayList<Gadget>());
    unitUnderTest.setHighestTrophies(815);
    unitUnderTest.setId(4711L);
    unitUnderTest.setName("astring");
    unitUnderTest.setPower(815);
    unitUnderTest.setRank(815);
    unitUnderTest.setStarPowers(new ArrayList<StarPower>());
    unitUnderTest.setTrophies(815);
    String
        expected =
        "Brawler(gadgets=" + new ArrayList<Gadget>()
            + ", highestTrophies=815, id=4711, name=astring, power=815, rank=815, starPowers="
            + new ArrayList<StarPower>() + ", trophies=815)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}