package brawljars.api.intern.rankings.brawler;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Club {

  @SerializedName("name")
  private String name;

}