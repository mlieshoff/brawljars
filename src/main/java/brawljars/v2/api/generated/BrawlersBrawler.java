package brawljars.v2.api.generated;

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
  private final Long id;
  @SerializedName("name")
  private final String name;
  @SerializedName("starPowers")
  private final List<BrawlersStarPower> starPowers;
  @SerializedName("gadgets")
  private final List<BrawlersGadget> gadgets;

}