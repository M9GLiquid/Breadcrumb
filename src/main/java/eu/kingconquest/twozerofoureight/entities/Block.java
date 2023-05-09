package eu.kingconquest.twozerofoureight.entities;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.twozerofoureight.core.EntityType2048;

public class Block extends Entity {

    public Block(Location location, EntityType2048 entityType2048){
        super(location, entityType2048);
    }
}
