package eu.kingconquest.Entities;

import eu.kingconquest.Core.Tile;
import eu.kingconquest.Utils.Location;

public class Wall extends Tile {
    public Wall(Location location) {
        super(location, "wall.png", false);
    }
}
