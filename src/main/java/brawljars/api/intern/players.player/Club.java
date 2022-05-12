package brawljars.api.intern.players.player;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Club {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;

}