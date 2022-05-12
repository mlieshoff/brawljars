package brawljars.api.intern.players.player;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Gear {

  @SerializedName("id")
  private long id;
  @SerializedName("name")
  private String name;
  @SerializedName("level")
  private int level;

}