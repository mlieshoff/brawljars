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
package brawljars.api.intern.club;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;

public class ClubApiImplIntegrationTest extends IntegrationTestBase {

  private ClubApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(ClubApi.class);
  }

  @Test
  void findClubMembers() throws Exception {
    String clubTag = "clubTag";
    brawljars.api.intern.club.ClubMembersRequest.ClubMembersRequestBuilder builder = brawljars.api.intern.club.ClubMembersRequest.builder(clubTag);
    brawljars.api.intern.club.ClubMembersRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();
    prepare("/clubs/{clubTag}/members".replace("{clubTag}", String.valueOf(clubTag)), "src/test/resources/club-findClubMembers.json", request);
    brawljars.api.intern.club.ClubMembersResponse expected = toJson(brawljars.api.intern.club.ClubMembersResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findClubMembers(request).get());
  }

  @Test
  void findClubMembers_whenWithException() {
    String clubTag = "clubTag";
    brawljars.api.intern.club.ClubMembersRequest.ClubMembersRequestBuilder builder = brawljars.api.intern.club.ClubMembersRequest.builder(clubTag);
    brawljars.api.intern.club.ClubMembersRequest request = builder      .limit(100)
      .before("zzz")
      .after("aaa")
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/clubs/{clubTag}/members".replace("{clubTag}", String.valueOf(clubTag)), request, () -> unitUnderTest.findClubMembers(request).get());
  }

  @Test
  void findClub() throws Exception {
    String clubTag = "clubTag";
    brawljars.api.intern.club.ClubRequest.ClubRequestBuilder builder = brawljars.api.intern.club.ClubRequest.builder(clubTag);
    brawljars.api.intern.club.ClubRequest request = builder
      .storeRawResponse(true)
      .build();
    prepare("/clubs/{clubTag}".replace("{clubTag}", String.valueOf(clubTag)), "src/test/resources/club-findClub.json", request);
    brawljars.api.intern.club.ClubResponse expected = toJson(brawljars.api.intern.club.ClubResponse.class, getExpected());

    run(expected, () -> unitUnderTest.findClub(request).get());
  }

  @Test
  void findClub_whenWithException() {
    String clubTag = "clubTag";
    brawljars.api.intern.club.ClubRequest.ClubRequestBuilder builder = brawljars.api.intern.club.ClubRequest.builder(clubTag);
    brawljars.api.intern.club.ClubRequest request = builder
      .storeRawResponse(true)
      .build();

    prepareWithErrorAndRun("/clubs/{clubTag}".replace("{clubTag}", String.valueOf(clubTag)), request, () -> unitUnderTest.findClub(request).get());
  }

}