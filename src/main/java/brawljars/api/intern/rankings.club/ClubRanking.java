package brawljars.api.intern.rankings.club;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ClubRanking {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;
  @SerializedName("badgeId")
  private long badgeId;
  @SerializedName("trophies")
  private int trophies;
  @SerializedName("rank")
  private int rank;
  @SerializedName("memberCount")
  private int memberCount;

}