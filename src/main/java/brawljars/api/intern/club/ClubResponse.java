package brawljars.api.intern.club;

import brawljars.common.IResponse;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClubResponse extends Club implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

}