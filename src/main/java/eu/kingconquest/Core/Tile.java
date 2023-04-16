package eu.kingconquest.Core;

import eu.kingconquest.Entities.Entity;
import eu.kingconquest.Utils.Location;

public class Tile extends Entity {
    public static final int TILE_SIZE = 32;
    private final boolean isWalkable;

    public Tile(Location location, EntityType entityType, boolean isWalkable) {
        super(location, entityType);
        this.isWalkable = isWalkable;
    }

    public boolean isWalkable(){
        return isWalkable;
    }
}
