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
class ClubMemberTest {

  private ClubMember unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = new ClubMember();
  }

  @Test
  void setIcon_whenWithValidParameter_thenSetIcon() {
    ClubIcon expected = new ClubIcon();
    unitUnderTest.setIcon(expected);

    assertEquals(expected, unitUnderTest.getIcon());
  }

  @Test
  void setName_whenWithValidParameter_thenSetName() {
    String expected = "astring";
    unitUnderTest.setName(expected);

    assertEquals(expected, unitUnderTest.getName());
  }

  @Test
  void setNameColor_whenWithValidParameter_thenSetNameColor() {
    String expected = "astring";
    unitUnderTest.setNameColor(expected);

    assertEquals(expected, unitUnderTest.getNameColor());
  }

  @Test
  void setRole_whenWithValidParameter_thenSetRole() {
    String expected = "astring";
    unitUnderTest.setRole(expected);

    assertEquals(expected, unitUnderTest.getRole());
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
  void toString_whenCalled_thenReturnStringRepresentation() {
    unitUnderTest.setIcon(new ClubIcon());
    unitUnderTest.setName("astring");
    unitUnderTest.setNameColor("astring");
    unitUnderTest.setRole("astring");
    unitUnderTest.setTag("astring");
    unitUnderTest.setTrophies(815);
    String expected = "ClubMember(icon=" + new ClubIcon() + ", name=astring, nameColor=astring, role=astring, tag=astring, trophies=815)";
    String actual = unitUnderTest.toString();

    assertEquals(expected, actual);
  }

}