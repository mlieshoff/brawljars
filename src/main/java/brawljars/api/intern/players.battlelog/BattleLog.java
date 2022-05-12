package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BattleLog {

  @SerializedName("battleTime")
  private String battleTime;
  @SerializedName("event")
  private Event event;
  @SerializedName("battle")
  private Battle battle;

}