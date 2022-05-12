package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Event {

  @SerializedName("id")
  private long id;
  @SerializedName("mode")
  private String mode;
  @SerializedName("map")
  private String map;

}