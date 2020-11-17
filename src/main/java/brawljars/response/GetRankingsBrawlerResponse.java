package brawljars.response;

import brawljars.model.RankingsBrawler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsBrawlerResponse extends PageableResponse<RankingsBrawler> {

}
