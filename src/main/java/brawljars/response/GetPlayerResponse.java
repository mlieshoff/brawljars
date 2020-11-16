package brawljars.response;

import com.google.gson.annotations.SerializedName;

import brawljars.model.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetPlayerResponse extends Player implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

}
