package brawljars.generated;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@ToString
@EqualsAndHashCode
public class BrawlerBrawler {

  @SerializedName("id")
  private Long id;
  @SerializedName("name")
  private String name;
  @SerializedName("starPowers")
  private List<BrawlerBrawlerStarPower> starPowers;
  @SerializedName("gadgets")
  private List<BrawlerBrawlerGadget> gadgets;

}