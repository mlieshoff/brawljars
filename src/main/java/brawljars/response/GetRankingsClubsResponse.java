package brawljars.response;

import brawljars.model.RankingsClub;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsClubsResponse extends PageableResponse<RankingsClub> {

}
