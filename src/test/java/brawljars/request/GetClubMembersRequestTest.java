package brawljars.request;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetClubMembersRequestTest extends PageableRequestTestBase<GetClubMembersRequest> {

  public static final String CLUB_TAG = "clubTag";

  @Test
  void build_whenWithNullClubTag_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetClubMembersRequest.builder(null).build());
  }

  @Test
  void build_whenWithEmptyClubTag_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetClubMembersRequest.builder("").build());
  }

  @Test
  void build_whenWithClubTag_thenSetValue() throws Exception {

    assertEquals(CLUB_TAG, GetClubMembersRequest.builder(CLUB_TAG).build().getClubTag());
  }

  @Test
  void build_whenWithClubTag_thenHaveRestParameter() throws Exception {

    assertEquals(singletonList(CLUB_TAG), GetClubMembersRequest.builder(CLUB_TAG).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetClubMembersRequest.builder(CLUB_TAG);
  }

}