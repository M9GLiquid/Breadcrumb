package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.entities.Entity;
import eu.kingconquest.framework.utils.Location;

public class Crate extends Entity {

    public Crate(Location location) {
        super(location, SokobanEntityIcon.CRATE);
    }
}
