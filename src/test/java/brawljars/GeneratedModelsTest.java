package brawljars;

import static org.assertj.core.api.Assertions.assertThat;

import brawljars.api.generated.model.Brawler;
import brawljars.api.generated.model.Club;
import brawljars.api.generated.model.Player;
import brawljars.api.intern.events.EventRotationResponse;
import brawljars.api.intern.players.battlelog.BattleLogResponse;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;

class GeneratedModelsTest {

    private final Gson gson = new Gson();

    @Test
    void deserialize_whenArraysAbsent_thenKeepNullDefaults() {
        assertThat(gson.fromJson("{}", Brawler.class).getGadgets()).isNull();
    }

    @Test
    void deserialize_whenArraysEmpty_thenKeepEmptyCollections() {
        assertThat(gson.fromJson("{\"gadgets\":[]}", Brawler.class).getGadgets()).isEmpty();
    }

    @Test
    void deserialize_whenClubInviteOnly_thenUseWireEnum() {
        assertThat(gson.fromJson("{\"type\":\"inviteOnly\"}", Club.class).getType())
                .isEqualTo(Club.TypeEnum.INVITE_ONLY);
    }

    @Test
    void deserialize_whenPlayerHasLocalizedName_thenKeepString() {
        assertThat(gson.fromJson("{\"name\":\"Shelly\"}", Player.class).getName())
                .isEqualTo("Shelly");
    }

    @Test
    void deserialize_whenEventRotationEmpty_thenReturnEmptyList() {
        assertThat(gson.fromJson("[]", EventRotationResponse.class)).isEmpty();
    }

    @Test
    void deserialize_whenBattleLogEmpty_thenReturnEmptyItems() {
        assertThat(gson.fromJson("{\"items\":[]}", BattleLogResponse.class).getItems()).isEmpty();
    }
}
