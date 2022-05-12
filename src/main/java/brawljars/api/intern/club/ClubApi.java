package brawljars.api.intern.club;

import java.util.concurrent.Future;
import brawljars.api.Api;

public interface ClubApi extends Api {

  Future<ClubMembersResponse> findClubMembers(ClubMembersRequest clubMembersRequest);
  Future<ClubResponse> findClub(ClubRequest clubRequest);

}
