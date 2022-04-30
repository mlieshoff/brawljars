package brawljars.api.intern.club;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Data;

@Data
public class Club {

  @SerializedName("tag")
  private String tag;
  @SerializedName("name")
  private String name;
  @SerializedName("description")
  private String description;
  @SerializedName("type")
  private String type;
  @SerializedName("badgeId")
  private long badgeId;
  @SerializedName("requiredTrophies")
  private int requiredTrophies;
  @SerializedName("trophies")
  private int trophies;
  @SerializedName("members")
  private List<Member> members;

}