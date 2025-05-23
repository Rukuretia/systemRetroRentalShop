package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositionTest {

    private AssetTag tag1;
    private AssetTag tag2;


    @BeforeEach
    void setUp() {
        tag1 = new AssetTag("TAG001", AssetTag.Status.ON_STOCK);
        tag2 = new AssetTag("TAG002", AssetTag.Status.ON_STOCK);
    }

    @Test
    void createGameTest() {
        //you cannot create a game without an asset tag
        assertThrows(NullPointerException.class, () -> { new Game("Some Game", Game.LanguageVersion.EN, Game.Genre.RPG, 10.0, null); }, "It's shouldn't be allowed to create the 'part' without the 'whole'");
        Game g1 = new Game("Some Game", Game.LanguageVersion.EN, Game.Genre.RPG, 10.0, tag1);
        assertEquals(tag1, g1.getAssetTag(), "The 'part' has no reference to the 'whole'");
        assertTrue(tag1.getGame().equals(g1), "The 'whole' has no reference to the 'part'");
    }

    @Test
    void removeGameFromAssetTagTest() {
        Game g1 = new Game("Some Game", Game.LanguageVersion.EN, Game.Genre.RPG, 10.0, tag1);

        assertEquals(tag1, g1.getAssetTag(), "The 'part' has no reference to the 'whole'");
        assertEquals(g1, tag1.getGame(), "The 'whole' has no reference to the 'part'");

        // Remove association from AssetTag side
        tag1.removeGame(g1);

        assertNull(g1.getAssetTag(), "After removing the relation, the 'part' should have no reference to the 'whole'");
        assertNull(tag1.getGame(), "After removing the relation, the 'whole' should have no reference to the 'part'");
    }

    @Test
    void switchGameAssetTagTest() {
        Game g1 = new Game("Some Game", Game.LanguageVersion.EN, Game.Genre.RPG, 10.0, tag1);

        assertEquals(tag1, g1.getAssetTag(), "The 'part' has no reference to the 'whole'");
        assertEquals(g1, tag1.getGame(), "The 'whole' has no reference to the 'part'");

        //it's not possible to overwrite child without removing it first
        assertThrows(IllegalArgumentException.class, () -> {
            new Game("Another Game", Game.LanguageVersion.EN, Game.Genre.RPG, 15.0, tag1);
        }, "It shouldn't be allowed to assign the 'part' to a different 'whole'");

        // Remove association from Game side this time
        assertThrows(RuntimeException.class, () -> {
            g1.destroy(tag1);
        }, "It shouldn't be allowed to destroy a part when it's whole has an association to it");
        tag1.removeGame(g1);
        assertNull(g1.getAssetTag(), "After removing the relation, the 'part' should have no reference to the 'whole'");
        assertNull(tag1.getGame(), "After removing the relation, the 'whole' should have no reference to the 'part'");

        // Adding a new game
        Game g2 = new Game("Another Game", Game.LanguageVersion.EN, Game.Genre.RPG, 15.0, tag1);
        assertEquals(tag1, g2.getAssetTag(), "The 'part' has no reference to the 'whole'");
        assertTrue(tag1.getGame().equals(g2), "The 'whole' has no reference to the 'part'");

        //it's not possible to change an owner of a composed object
        assertThrows(RuntimeException.class, () -> {
            g2.setAssetTag(tag2);
        });
    }
}