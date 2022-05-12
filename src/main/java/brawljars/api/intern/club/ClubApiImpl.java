package brawljars.api.intern.club;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.BaseApi;

class ClubApiImpl extends BaseApi implements ClubApi {

  ClubApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<ClubMembersResponse> findClubMembers(ClubMembersRequest clubMembersRequest) {
    return get("/clubs/{clubTag}/members", clubMembersRequest, ClubMembersResponse.class);
  }

  public Future<ClubResponse> findClub(ClubRequest clubRequest) {
    return get("/clubs/{clubTag}", clubRequest, ClubResponse.class);
  }

}