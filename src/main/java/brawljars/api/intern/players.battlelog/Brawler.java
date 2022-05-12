package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Brawler {

  @SerializedName("id")
  private int id;
  @SerializedName("name")
  private String name;
  @SerializedName("power")
  private int power;
  @SerializedName("trophies")
  private int trophies;

}