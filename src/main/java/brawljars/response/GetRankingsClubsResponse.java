package brawljars.response;

import brawljars.request.GetRankingsClubsRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetRankingsClubsResponse extends PageableResponse<GetRankingsClubsRequest> {

}