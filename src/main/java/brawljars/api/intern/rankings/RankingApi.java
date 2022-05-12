package brawljars.api.intern.rankings;

import java.util.concurrent.Future;
import brawljars.api.Api;
import brawljars.api.intern.rankings.brawler.BrawlerRankingsRequest;
import brawljars.api.intern.rankings.brawler.BrawlerRankingsResponse;
import brawljars.api.intern.rankings.club.ClubRankingsRequest;
import brawljars.api.intern.rankings.club.ClubRankingsResponse;
import brawljars.api.intern.rankings.player.PlayerRankingsRequest;
import brawljars.api.intern.rankings.player.PlayerRankingsResponse;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsRequest;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsResponse;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsRequest;
import brawljars.api.intern.rankings.powerplay.PowerplayRankingsSeasonsResponse;

public interface RankingApi extends Api {

  Future<PowerplayRankingsResponse> findPowerplayRankings(PowerplayRankingsRequest powerplayRankingsRequest);
  Future<PowerplayRankingsSeasonsResponse> findPowerplayRankingsSeasons(PowerplayRankingsSeasonsRequest powerplayRankingsSeasonsRequest);
  Future<ClubRankingsResponse> findClubRankings(ClubRankingsRequest clubRankingsRequest);
  Future<BrawlerRankingsResponse> findBrawlerRankings(BrawlerRankingsRequest brawlerRankingsRequest);
  Future<PlayerRankingsResponse> findPlayerRankings(PlayerRankingsRequest playerRankingsRequest);

}
