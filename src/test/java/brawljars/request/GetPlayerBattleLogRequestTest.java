package brawljars.request;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetPlayerBattleLogRequestTest extends PageableRequestTestBase<GetPlayerBattleLogRequest> {

  public static final String PLAYER_TAG = "playerTag";

  @Test
  void build_whenWithNullPlayerTag_thenThrowException() throws Exception {

    assertThrows(NullPointerException.class, () -> GetPlayerBattleLogRequest.builder(null).build());
  }

  @Test
  void build_whenWithEmptyPlayerTag_thenThrowException() throws Exception {

    assertThrows(IllegalArgumentException.class, () -> GetPlayerBattleLogRequest.builder("").build());
  }

  @Test
  void build_whenWithPlayerTag_thenSetValue() throws Exception {

    assertEquals(PLAYER_TAG, GetPlayerBattleLogRequest.builder(PLAYER_TAG).build().getPlayerTag());
  }

  @Test
  void build_whenWithPlayerTag_thenHaveRestParameter() throws Exception {

    assertEquals(singletonList(PLAYER_TAG), GetPlayerBattleLogRequest.builder(PLAYER_TAG).build().getRestParameters());
  }

  @Override
  Object getBuilder() {
    return GetPlayerBattleLogRequest.builder(PLAYER_TAG);
  }

}