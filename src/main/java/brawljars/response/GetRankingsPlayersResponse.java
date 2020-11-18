package brawljars.response;

import brawljars.model.RankingsPlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsPlayersResponse extends PageableResponse<RankingsPlayer> {

}
