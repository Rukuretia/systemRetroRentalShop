package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAssociationTest {
    Game g1;
    Studio s1;
    Studio s2;

    @BeforeEach
    void setup() {
        g1 = new Game("Monster Strike", Game.LanguageVersion.EN, Game.Genre.RPG, 59.99, null);
        s1 = new Studio("Capcom", Studio.Country.PL); // or whichever Country enum value is appropriate
        s2 = new Studio("Bandai", Studio.Country.US);
    }

    @Test
    void setStudioTest() {
        g1.setStudio(s1);
        assertEquals(s1, g1.getStudio(), "The Studio object relation is not present in the Game object");
        assertTrue(s1.getGames().contains(g1), "The Game object relation is not present in the Studio object");
    }

    @Test
    void addGameTest() {
        assertThrows(NullPointerException.class, () -> s1.addGame(null), "You should not allow to create an association with null");
        s1.addGame(g1);
        assertEquals(s1, g1.getStudio(), "The Studio object relation is not present in the Game object");
        assertTrue(s1.getGames().contains(g1), "The Game object relation is not present in the Studio object");
    }

    @Test
    void addAndRemoveFromGame() {
        g1.setStudio(s1);
        assertEquals(s1, g1.getStudio(), "The Studio object relation is not present in the Game object");
        assertTrue(s1.getGames().contains(g1), "The Game object relation is not present in the Studio object");

        g1.setStudio(null);
        assertNull(g1.getStudio(), "After removing Studio from Game, the latter should have null ref to Studio");
        assertFalse(s1.getGames().contains(g1), "After removing Studio from Game, the former should not have reference to the removed Game");
    }

    @Test
    void addAndRemoveFromStudio() {
        g1.setStudio(s1);
        assertEquals(s1, g1.getStudio(), "The Studio object relation is not present in the Game object");
        assertTrue(s1.getGames().contains(g1), "The Game object relation is not present in the Studio object");

        s1.removeGame(g1);
        assertNull(g1.getStudio(), "After removing Game from Studio, the former should have null ref to Studio");
        assertFalse(s1.getGames().contains(g1), "After removing Game from Studio, the latter should not have reference to the removed Game");
    }

    @Test
    void testReplaceStudio() {
        g1.setStudio(s1);
        assertEquals(s1, g1.getStudio(), "The Studio object relation is not present in the Game object");
        assertTrue(s1.getGames().contains(g1), "The Game object relation is not present in the Studio object");

        g1.setStudio(s2);
        assertFalse(s1.getGames().contains(g1), "After setting a new Studio for a Game, the Game should be removed from the former Studio");
        assertEquals(s2, g1.getStudio(), "The new Studio object relation is not present in the Game object");
        assertTrue(s2.getGames().contains(g1), "The Game object relation is not present in the new Studio object");
    }
}