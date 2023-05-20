package eu.kingconquest.twozerofoureight.entities;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityIcon;
import eu.kingconquest.framework.utils.Location;

/**
 * Represents a Block in the game, extending the Entity class.
 */
public class Block extends Entity {
    /**
     * Constructs a Block object with the specified location and entity type.
     * @param location The location of the block.
     * @param entityType The entity type of the block.
     */
    public Block(Location location, EntityIcon entityType){
        super(location, entityType);
    }
}
