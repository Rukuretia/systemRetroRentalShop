package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QualifiedTest {

    Game game;
    Platform platform;

    @BeforeEach
    void setup() {
        AssetTag assetTag = new AssetTag("TAG001", AssetTag.Status.ON_STOCK);
        game = new Game("Monster Quest", Game.LanguageVersion.EN, Game.Genre.RPG, 39.99, assetTag);
        platform = new Platform("PS5", "Sony", Platform.Type.STATIONARY);
    }

    @Test
    void createTest() {
        game.addPlatform(platform);
        assertTrue(game.getPlatforms().get(platform.getName()) == platform);
        assertTrue(platform.getGames().contains(game));

        // check if getPlatformByName works properly
        assertEquals(platform, game.getPlatforms().get(platform.getName()));
    }

    @Test
    void createTest2() {
        platform.addGame(game);
        assertTrue(game.getPlatforms().get(platform.getName()) == platform);
        assertTrue(platform.getGames().contains(game));

        // check if getPlatformByName works properly
        assertEquals(platform, game.getPlatforms().get(platform.getName()));
    }

    @Test
    void removeTest() {
        game.addPlatform(platform);
        assertTrue(game.getPlatforms().get(platform.getName()) == platform);
        assertTrue(platform.getGames().contains(game));

        game.removePlatform(platform);
        assertFalse(platform.getGames().contains(game));
        assertFalse(game.getPlatforms().containsKey(platform.getName()));
    }

    @Test
    void removeTest2() {
        game.addPlatform(platform);
        assertTrue(game.getPlatforms().get(platform.getName()) == platform);
        assertTrue(platform.getGames().contains(game));

        platform.removeGame(game);
        assertFalse(platform.getGames().contains(game));
        assertFalse(game.getPlatforms().containsKey(platform.getName()));
    }
}