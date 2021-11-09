package brawljars.generated;

import brawljars.common.IResponse;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BrawlerResponse extends BrawlerBrawler implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

}