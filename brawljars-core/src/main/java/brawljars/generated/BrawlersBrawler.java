package brawljars.generated;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@ToString
@EqualsAndHashCode
public class BrawlersBrawler {

  @SerializedName("id")
  private Long id;
  @SerializedName("name")
  private String name;
  @SerializedName("starPowers")
  private List<BrawlersBrawlerStarPower> starPowers;
  @SerializedName("gadgets")
  private List<BrawlersBrawlerGadget> gadgets;

}