package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class StarPlayer {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;
  @SerializedName("brawler")
  private Brawler brawler;

}