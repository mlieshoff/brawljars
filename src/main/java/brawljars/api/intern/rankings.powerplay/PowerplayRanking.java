package brawljars.api.intern.rankings.powerplay;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PowerplayRanking {

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