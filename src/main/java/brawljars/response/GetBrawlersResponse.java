package brawljars.response;

import brawljars.model.Brawler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetBrawlersResponse extends PageableResponse<Brawler> {

}
