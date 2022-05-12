package brawljars.api.intern.rankings.player;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PlayerRanking {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;
  @SerializedName("nameColor")
  private String nameColor;
  @SerializedName("icon")
  private Icon icon;
  @SerializedName("trophies")
  private int trophies;
  @SerializedName("rank")
  private int rank;
  @SerializedName("club")
  private Club club;

}