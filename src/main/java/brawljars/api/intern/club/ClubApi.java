package brawljars.api.intern.club;

import java.io.IOException;
import java.util.concurrent.Future;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface ClubApi extends Api {

  Future<brawljars.api.intern.club.ClubMembersResponse> findClubMembers(brawljars.api.intern.club.ClubMembersRequest clubMembersRequest) throws ApiException;
  Future<brawljars.api.intern.club.ClubResponse> findClub(brawljars.api.intern.club.ClubRequest clubRequest) throws ApiException;

}
