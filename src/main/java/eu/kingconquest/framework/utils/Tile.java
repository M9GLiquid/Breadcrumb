package eu.kingconquest.framework.utils;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityType;

/**
 * Represents a Tile on the game board, which is a subclass of Entity.
 * A Tile has a specific location on the game board, an EntityType to describe its appearance and behavior,
 * and a flag indicating if it is walkable or not. The Tile class also stores a static size value for all Tile instances.
 */
public class Tile extends Entity {
    private static int TILE_SIZE = 32;
    private final boolean isWalkable;

    /**
     * Constructs a Tile with the specified location, EntityType, and walkability.
     * This constructor initializes the Tile instance with the provided parameters.
     *
     * @param location the location of the Tile on the game board
     * @param entityType the EntityType of the Tile, which defines its appearance and behavior
     * @param isWalkable a boolean indicating whether the Tile can be walked on by other entities
     */
    public Tile(Location location, EntityType entityType, boolean isWalkable) {
        super(location, entityType);
        this.isWalkable = isWalkable;
    }

    /**
     * Returns whether the Tile is walkable.
     * This method checks if other entities can move onto this Tile.
     *
     * @return true if the Tile is walkable, false otherwise
     */
    public boolean isWalkable(){
        return isWalkable;
    }

    /**
     * Sets the size of the Tile.
     * This method updates the static size value for all Tile instances.
     *
     * @param size the new size for the Tile
     */
    public static void setTileSize(int size){
        TILE_SIZE = size;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public String toString(){
        return getEntityType().toString();
    }
}
