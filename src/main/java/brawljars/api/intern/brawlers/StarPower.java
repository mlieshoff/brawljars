package brawljars.api.intern.brawlers;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class StarPower {

  @SerializedName("id")
  private long id;
  @SerializedName("name")
  private String name;

}