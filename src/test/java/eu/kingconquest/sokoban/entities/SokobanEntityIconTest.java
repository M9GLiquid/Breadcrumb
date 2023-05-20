package eu.kingconquest.sokoban.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SokobanEntityIconTest {

    @Test
    public void checkSymbols() {
        assertEquals("#", SokobanEntityIcon.WALL.toString());
        assertEquals("P", SokobanEntityIcon.PLAYER.toString());
        assertEquals("-", SokobanEntityIcon.GROUND.toString());
        assertEquals("X", SokobanEntityIcon.GROUND_MARKED.toString());
        assertEquals("C", SokobanEntityIcon.CRATE.toString());
        assertEquals("M", SokobanEntityIcon.CRATE_MARKED.toString());
    }

    @Test
    public void checkIcons() {
        // Note: You'll need some way to check that the images are correct.
        // For simplicity, we'll just check that they're not null.
        assertNotNull(SokobanEntityIcon.WALL.getIcon());
        assertNotNull(SokobanEntityIcon.PLAYER.getIcon());
        assertNotNull(SokobanEntityIcon.GROUND.getIcon());
        assertNotNull(SokobanEntityIcon.GROUND_MARKED.getIcon());
        assertNotNull(SokobanEntityIcon.CRATE.getIcon());
        assertNotNull(SokobanEntityIcon.CRATE_MARKED.getIcon());
    }
}
