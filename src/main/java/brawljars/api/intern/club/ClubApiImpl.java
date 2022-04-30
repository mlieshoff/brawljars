package brawljars.api.intern.club;

import java.util.concurrent.Future;
import brawljars.api.ApiContext;
import brawljars.api.ApiException;
import brawljars.api.BaseApi;

class ClubApiImpl extends BaseApi implements ClubApi {

  public ClubApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<brawljars.api.intern.club.ClubMembersResponse> findClubMembers(brawljars.api.intern.club.ClubMembersRequest clubMembersRequest) throws ApiException {
    return get("/clubs/{clubTag}/members", clubMembersRequest, brawljars.api.intern.club.ClubMembersResponse.class);
  }

  public Future<brawljars.api.intern.club.ClubResponse> findClub(brawljars.api.intern.club.ClubRequest clubRequest) throws ApiException {
    return get("/clubs/{clubTag}", clubRequest, brawljars.api.intern.club.ClubResponse.class);
  }

}