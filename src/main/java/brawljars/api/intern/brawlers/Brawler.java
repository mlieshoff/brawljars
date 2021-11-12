package brawljars.api.intern.brawlers;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Brawler {

  @SerializedName("id")
  private long id;
  @SerializedName("name")
  private String name;
  @SerializedName("starPowers")
  private List<StarPower> starPowers;
  @SerializedName("gadgets")
  private List<Gadget> gadgets;

}