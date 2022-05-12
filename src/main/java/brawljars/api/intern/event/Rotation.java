package brawljars.api.intern.event;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Rotation {

  @SerializedName("startTime")
  private String startTime;
  @SerializedName("endTime")
  private String endTime;
  @SerializedName("slotId")
  private long slotId;
  @SerializedName("event")
  private Event event;

}