package brawljars.api.intern.club;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  brawljars.api.intern.club.ClubMembersRequest request = brawljars.api.intern.club.ClubMembersRequest.builder(clubTag)
      .limit(100)
      .before("zzz")
      .after("aaa")
      .build();
    prepare("/clubs/{clubTag}/members".replace("{clubTag}", String.valueOf(clubTag)), "src/test/resources/club-findClubMembers.json", request);

    brawljars.api.intern.club.ClubMembersResponse actual = unitUnderTest.findClubMembers(request).get();
    brawljars.api.intern.club.ClubMembersResponse expected = toJson(brawljars.api.intern.club.ClubMembersResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findClub() throws Exception {
    String clubTag = "clubTag";
  brawljars.api.intern.club.ClubRequest request = brawljars.api.intern.club.ClubRequest.builder(clubTag)
      .build();
    prepare("/clubs/{clubTag}".replace("{clubTag}", String.valueOf(clubTag)), "src/test/resources/club-findClub.json", request);

    brawljars.api.intern.club.ClubResponse actual = unitUnderTest.findClub(request).get();
    brawljars.api.intern.club.ClubResponse expected = toJson(brawljars.api.intern.club.ClubResponse.class, getExpected());

    assertEquals(expected, actual);
  }

}