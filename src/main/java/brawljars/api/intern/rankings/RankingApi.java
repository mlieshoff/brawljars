package brawljars.api.intern.rankings;

import java.io.IOException;
import java.util.concurrent.Future;
import brawljars.api.Api;
import brawljars.api.ApiException;

public interface RankingApi extends Api {

  Future<brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse> findPowerplayRankings(brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest powerplayRankingsRequest) throws ApiException;
  Future<brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse> findPowerplayRankingsSeasons(brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest) throws ApiException;
  Future<brawljars.api.intern.rankings.club.ClubRankingsResponse> findClubRankings(brawljars.api.intern.rankings.club.ClubRankingsRequest clubRankingsRequest) throws ApiException;
  Future<brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse> findBrawlerRankings(brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest brawlerRankingsRequest) throws ApiException;
  Future<brawljars.api.intern.rankings.player.PlayerRankingsResponse> findPlayerRankings(brawljars.api.intern.rankings.player.PlayerRankingsRequest playerRankingsRequest) throws ApiException;

}
