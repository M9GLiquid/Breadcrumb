package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.entities.Entity;
import eu.kingconquest.framework.utils.Location;

public class Player extends Entity {
    public Player(Location location){
        super(location, SokobanEntityIcon.PLAYER);
    }
}
