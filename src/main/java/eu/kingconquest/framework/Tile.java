package eu.kingconquest.framework;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityType;
import eu.kingconquest.framework.utils.Location;

public class Tile extends Entity {
    public static int TILE_SIZE = 32;
    private final boolean isWalkable;

    public Tile(Location location, EntityType entityType, boolean isWalkable) {
        super(location, entityType);
        this.isWalkable = isWalkable;
    }

    public boolean isWalkable(){
        return isWalkable;
    }

    public void setTileSize(int size){
        TILE_SIZE = size;
    }
}
