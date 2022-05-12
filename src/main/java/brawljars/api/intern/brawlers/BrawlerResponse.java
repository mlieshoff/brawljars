package brawljars.api.intern.brawlers;

import com.google.gson.annotations.SerializedName;

import brawljars.common.IResponse;
import brawljars.common.RawResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BrawlerResponse extends Brawler implements IResponse {

  @SerializedName("reason")
  private String reason;

  @SerializedName("message")
  private String message;

  private transient RawResponse rawResponse;

}