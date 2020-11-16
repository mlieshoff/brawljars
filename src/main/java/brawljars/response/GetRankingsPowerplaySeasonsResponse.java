package brawljars.response;

import brawljars.model.RankingsPowerplaySeason;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsPowerplaySeasonsResponse extends PageableResponse<RankingsPowerplaySeason> {

}
