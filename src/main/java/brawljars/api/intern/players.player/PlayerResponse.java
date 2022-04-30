package brawljars.api.intern.players.player;

import brawljars.common.IResponse;

import com.google.gson.annotations.SerializedName;

import brawljars.common.RawResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerResponse extends Player implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

  private transient RawResponse rawResponse;

}