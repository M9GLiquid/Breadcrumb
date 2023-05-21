package eu.kingconquest.twozerofoureight.entities;

import eu.kingconquest.framework.entities.Entity;
import eu.kingconquest.framework.entities.EntityIcon;
import eu.kingconquest.framework.utils.Location;

/**
 * Represents a Block in the game, extending the Entity class.
 */
public class Block extends Entity {
    /**
     * Constructs a Block object with the specified location and entities type.
     * @param location The location of the block.
     * @param entityType The entities type of the block.
     */
    public Block(Location location, EntityIcon entityType){
        super(location, entityType);
    }
}
