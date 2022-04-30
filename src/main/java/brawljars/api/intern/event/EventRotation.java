package brawljars.api.intern.event;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EventRotation extends ArrayList<Rotation> {

}