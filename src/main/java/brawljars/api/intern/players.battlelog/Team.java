package brawljars.api.intern.players.battlelog;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Team extends ArrayList<TeamMember> {

}