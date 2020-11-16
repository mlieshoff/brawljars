package brawljars.response;

import brawljars.model.PlayerBattleLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetPlayerBattleLogResponse extends PageableResponse<PlayerBattleLog> {

}
