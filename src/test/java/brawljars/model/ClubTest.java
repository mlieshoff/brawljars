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
class ClubTest {

  private Club unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new Club();
  }

  @Test
  void setBadgeId_whenWithValidParameter_thenSetBadgeId() {
    int expected = 815;
    unitUnderTest.setBadgeId(expected);

    assertEquals(expected, unitUnderTest.getBadgeId());
  }

  @Test
  void setDescription_whenWithValidParameter_thenSetDescription() {
    String expected = "astring";
    unitUnderTest.setDescription(expected);

    assertEquals(expected, unitUnderTest.getDescription());
  }

  @Test
  void setMembers_whenWithValidParameter_thenSetMembers() {
    List<ClubMember> expected = new ArrayList<ClubMember>();
    unitUnderTest.setMembers(expected);

    assertEquals(expected, unitUnderTest.getMembers());
  }

  @Test
  void setName_whenWithValidParameter_thenSetName() {
    String expected = "astring";
    unitUnderTest.setName(expected);

    assertEquals(expected, unitUnderTest.getName());
  }

  @Test
  void setRequiredTrophies_whenWithValidParameter_thenSetRequiredTrophies() {
    int expected = 815;
    unitUnderTest.setRequiredTrophies(expected);

    assertEquals(expected, unitUnderTest.getRequiredTrophies());
  }

  @Test
  void setTag_whenWithValidParameter_thenSetTag() {
    String expected = "astring";
    unitUnderTest.setTag(expected);

    assertEquals(expected, unitUnderTest.getTag());
  }

  @Test
  void setTrophies_whenWithValidParameter_thenSetTrophies() {
    int expected = 815;
    unitUnderTest.setTrophies(expected);

    assertEquals(expected, unitUnderTest.getTrophies());
  }

  @Test
  void setType_whenWithValidParameter_thenSetType() {
    String expected = "astring";
    unitUnderTest.setType(expected);

    assertEquals(expected, unitUnderTest.getType());
  }

  @Test
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setBadgeId(815);
    unitUnderTest.setDescription("astring");
    unitUnderTest.setMembers(new ArrayList<ClubMember>());
    unitUnderTest.setName("astring");
    unitUnderTest.setRequiredTrophies(815);
    unitUnderTest.setTag("astring");
    unitUnderTest.setTrophies(815);
    unitUnderTest.setType("astring");
    String
        expected =
        "Club(badgeId=815, description=astring, members=" + new ArrayList<ClubMember>()
            + ", name=astring, requiredTrophies=815, tag=astring, trophies=815, type=astring)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}