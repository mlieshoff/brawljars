package brawljars.api.intern.players.player;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Brawler {

  @SerializedName("id")
  private long id;
  @SerializedName("name")
  private String name;
  @SerializedName("power")
  private int power;
  @SerializedName("rank")
  private int rank;
  @SerializedName("trophies")
  private int trophies;
  @SerializedName("highestTrophies")
  private int highestTrophies;
  @SerializedName("gears")
  private List<Gear> gears;
  @SerializedName("starPowers")
  private List<StarPower> starPowers;
  @SerializedName("gadgets")
  private List<Gadget> gadgets;

}