package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Battle {

  @SerializedName("mode")
  private String mode;
  @SerializedName("type")
  private String type;
  @SerializedName("result")
  private String result;
  @SerializedName("duration")
  private Integer duration;
  @SerializedName("rank")
  private Integer rank;
  @SerializedName("trophyChange")
  private Integer trophyChange;
  @SerializedName("starPlayer")
  private StarPlayer starPlayer;
  @SerializedName("teams")
  private List<Team> teams;
  @SerializedName("players")
  private List<TeamPlayer> players;

}