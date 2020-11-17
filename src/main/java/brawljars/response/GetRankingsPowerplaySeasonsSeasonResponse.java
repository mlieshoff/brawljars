package brawljars.response;

import brawljars.model.RankingsPowerplaySeasonsSeason;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsPowerplaySeasonsSeasonResponse extends PageableResponse<RankingsPowerplaySeasonsSeason> {

}
