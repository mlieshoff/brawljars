package brawljars.response;

import com.google.gson.annotations.SerializedName;

import brawljars.model.Brawler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetBrawlerResponse extends Brawler implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

}
