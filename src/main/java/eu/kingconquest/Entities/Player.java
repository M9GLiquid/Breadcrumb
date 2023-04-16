package eu.kingconquest.Entities;

import eu.kingconquest.Core.EntityType;
import eu.kingconquest.Utils.Location;

public class Player extends Entity {
    public Player(Location location){
        super(location, EntityType.PLAYER);
    }
}
