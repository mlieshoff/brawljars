package brawljars.response;

import brawljars.model.ClubMember;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetClubMembersResponse extends PageableResponse<ClubMember> {

}
