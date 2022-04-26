package brawljars.api.intern.players.player;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Gadget {

  @SerializedName("id")
  private long id;
  @SerializedName("name")
  private String name;

}