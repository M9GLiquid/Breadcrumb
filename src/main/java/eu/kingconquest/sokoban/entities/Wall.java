package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.Tile;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.sokoban.core.SokobanEntityType;

public class Wall extends Tile {
    public Wall(Location location) {
        super(location, SokobanEntityType.WALL, false);
    }
}
