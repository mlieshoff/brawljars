package brawljars.response;

import brawljars.model.RankingsPowerplaySeasons;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsClubsResponse extends PageableResponse<RankingsPowerplaySeasons> {

}
