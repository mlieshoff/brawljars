package brawljars.api.intern.club;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static wiremock.org.apache.commons.lang3.StringUtils.EMPTY;

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