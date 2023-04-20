package framework;

import eu.kingconquest.framework.Tile;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.sokoban.core.SokobanEntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileFrameworkTest {
    private Tile tile;
    private Location location;
    private SokobanEntityType entityType;

    @BeforeEach
    public void setUp() {
        location = new Location(0, 0);
        entityType = SokobanEntityType.GROUND;
        tile = new Tile(location, entityType, true);
    }

    @Test
    public void testIsWalkable() {
        assertTrue(tile.isWalkable(), "Tile should be walkable");
    }

    @Test
    public void testNonWalkableTile() {
        Tile nonWalkableTile = new Tile(location, entityType, false);
        assertFalse(nonWalkableTile.isWalkable(), "Tile should not be walkable");
    }

    @Test
    public void testSetTileSize() {
        int newSize = 64;
        tile.setTileSize(newSize);
        assertEquals(newSize, Tile.TILE_SIZE, "Tile size should be updated to the new size");
    }
}
