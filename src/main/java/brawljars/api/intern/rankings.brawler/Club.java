package brawljars.api.intern.rankings.brawler;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Club {

  @SerializedName("name")
  private String name;

}