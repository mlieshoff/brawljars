package brawljars.api.intern.rankings.powerplay;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PowerplayRankingSeason {

  @SerializedName("id")
  private String id;
  @SerializedName("startTime")
  private String startTime;
  @SerializedName("endTime")
  private String endTime;

}