package brawljars.api.intern.gamemodes;

import brawljars.IntegrationTestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameModeApiImplIntegrationTest extends IntegrationTestBase {
    private GameModeApi unitUnderTest;

    @BeforeEach
    void setUp() {
        unitUnderTest = getBrawlJars().getApi(GameModeApi.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"gamemode-findAll.json", "gamemode-findAll-empty.json"})
    void findAll_whenSuccessful_thenPreservePayloadAndRawResponse(String fixture) throws Exception {
        GameModesRequest request =
                GameModesRequest.builder().limit(5).after("next").storeRawResponse(true).build();
        prepare("/gamemodes", EMPTY, "src/test/resources/" + fixture, request);
        GameModesResponse expected = toJson(GameModesResponse.class, getExpected());

        run(expected, () -> unitUnderTest.findAll(request).get());
    }

    @Test
    void findAll_whenConnectorFails_thenPropagateError() {
        GameModesRequest request = GameModesRequest.builder().build();

        prepareWithErrorAndRun(
                "/gamemodes", EMPTY, request, () -> unitUnderTest.findAll(request).get());
    }
}
