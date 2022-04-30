package brawljars.api.intern.club;

import java.io.IOException;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface ClubApi extends Api {

  brawljars.api.intern.club.ClubMembersResponse findClubMembers(brawljars.api.intern.club.ClubMembersRequest clubMembersRequest) throws ApiException;
  brawljars.api.intern.club.ClubResponse findClub(brawljars.api.intern.club.ClubRequest clubRequest) throws ApiException;

}
