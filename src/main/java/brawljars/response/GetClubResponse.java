package brawljars.response;

import com.google.gson.annotations.SerializedName;

import brawljars.model.Club;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetClubResponse extends Club implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

}
