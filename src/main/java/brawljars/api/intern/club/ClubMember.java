package brawljars.api.intern.club;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class ClubMember {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;
  @SerializedName("nameColor")
  private String nameColor;
  @SerializedName("role")
  private String role;
  @SerializedName("trophies")
  private int trophies;
  @SerializedName("icon")
  private Icon icon;

}