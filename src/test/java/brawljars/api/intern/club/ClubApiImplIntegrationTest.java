package brawljars.api.intern.club;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brawljars.IntegrationTestBase;
import brawljars.common.BlockingCallback;

public class ClubApiImplIntegrationTest extends IntegrationTestBase {

  private ClubApi unitUnderTest;

  @BeforeEach
  void setUp() {
    unitUnderTest = getBrawlJars().getApi(ClubApi.class);
  }

  @Test
  void findClubMembers() throws Exception {
    run_findClubMembers(false);
  }

  void run_findClubMembers(boolean withCallback) throws Exception {
    String clubTag = "clubTag";
    brawljars.api.intern.club.ClubMembersRequest.ClubMembersRequestBuilder builder = brawljars.api.intern.club.ClubMembersRequest.builder(clubTag)
      .limit(100)
      .before("zzz")
      .after("aaa");
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.club.ClubMembersRequest request = builder.build();
    prepare("/clubs/{clubTag}/members".replace("{clubTag}", String.valueOf(clubTag)), "src/test/resources/club-findClubMembers.json", request);

    brawljars.api.intern.club.ClubMembersResponse actual;
    if (withCallback) {
      unitUnderTest.findClubMembers(request);
      actual = ((BlockingCallback<brawljars.api.intern.club.ClubMembersResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findClubMembers(request);
    }
    brawljars.api.intern.club.ClubMembersResponse expected = toJson(brawljars.api.intern.club.ClubMembersResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findClubMembers_withCallback() throws Exception {
    run_findClubMembers(true);
  }

  @Test
  void findClub() throws Exception {
    run_findClub(false);
  }

  void run_findClub(boolean withCallback) throws Exception {
    String clubTag = "clubTag";
    brawljars.api.intern.club.ClubRequest.ClubRequestBuilder builder = brawljars.api.intern.club.ClubRequest.builder(clubTag);
    if (withCallback) {
      builder.callback(new BlockingCallback<>());
    }
    brawljars.api.intern.club.ClubRequest request = builder.build();
    prepare("/clubs/{clubTag}".replace("{clubTag}", String.valueOf(clubTag)), "src/test/resources/club-findClub.json", request);

    brawljars.api.intern.club.ClubResponse actual;
    if (withCallback) {
      unitUnderTest.findClub(request);
      actual = ((BlockingCallback<brawljars.api.intern.club.ClubResponse>) request.getCallback()).get();
    } else {
      actual = unitUnderTest.findClub(request);
    }
    brawljars.api.intern.club.ClubResponse expected = toJson(brawljars.api.intern.club.ClubResponse.class, getExpected());

    assertEquals(expected, actual);
  }

  @Test
  void findClub_withCallback() throws Exception {
    run_findClub(true);
  }

}