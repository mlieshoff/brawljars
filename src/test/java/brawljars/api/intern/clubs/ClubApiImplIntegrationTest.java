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
package brawljars.api.intern.clubs;

import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

import brawljars.IntegrationTestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubApiImplIntegrationTest extends IntegrationTestBase {

    private ClubApi unitUnderTest;

    @BeforeEach
    void setUp() {
        unitUnderTest = getBrawlJars().getApi(ClubApi.class);
    }

    @Test
    void findClubMembers() throws Exception {
        String clubTag = "clubTag";
        brawljars.api.intern.clubs.member.ClubMembersRequest.ClubMembersRequestBuilder builder =
                brawljars.api.intern.clubs.member.ClubMembersRequest.builder(clubTag);
        brawljars.api.intern.clubs.member.ClubMembersRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();
        prepare(
                "/clubs/{clubTag}/members".replace("{clubTag}", clubTag),
                EMPTY,
                "src/test/resources/club-findClubMembers.json",
                request);
        brawljars.api.intern.clubs.member.ClubMembersResponse expected =
                toJson(brawljars.api.intern.clubs.member.ClubMembersResponse.class, getExpected());

        run(expected, () -> unitUnderTest.findClubMembers(request).get());
    }

    @Test
    void findClubMembers_whenWithException() {
        String clubTag = "clubTag";
        brawljars.api.intern.clubs.member.ClubMembersRequest.ClubMembersRequestBuilder builder =
                brawljars.api.intern.clubs.member.ClubMembersRequest.builder(clubTag);
        brawljars.api.intern.clubs.member.ClubMembersRequest request =
                builder.limit(100).before("zzz").after("aaa").storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/clubs/{clubTag}/members".replace("{clubTag}", clubTag),
                EMPTY,
                request,
                () -> unitUnderTest.findClubMembers(request).get());
    }

    @Test
    void findClub() throws Exception {
        String clubTag = "clubTag";
        brawljars.api.intern.clubs.info.ClubRequest.ClubRequestBuilder builder =
                brawljars.api.intern.clubs.info.ClubRequest.builder(clubTag);
        brawljars.api.intern.clubs.info.ClubRequest request =
                builder.storeRawResponse(true).build();
        prepare(
                "/clubs/{clubTag}".replace("{clubTag}", clubTag),
                EMPTY,
                "src/test/resources/club-findClub.json",
                request);
        brawljars.api.intern.clubs.info.ClubResponse expected =
                toJson(brawljars.api.intern.clubs.info.ClubResponse.class, getExpected());

        run(expected, () -> unitUnderTest.findClub(request).get());
    }

    @Test
    void findClub_whenWithException() {
        String clubTag = "clubTag";
        brawljars.api.intern.clubs.info.ClubRequest.ClubRequestBuilder builder =
                brawljars.api.intern.clubs.info.ClubRequest.builder(clubTag);
        brawljars.api.intern.clubs.info.ClubRequest request =
                builder.storeRawResponse(true).build();

        prepareWithErrorAndRun(
                "/clubs/{clubTag}".replace("{clubTag}", clubTag),
                EMPTY,
                request,
                () -> unitUnderTest.findClub(request).get());
    }
}
