package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.sokoban.core.SokobanEntityType;
import eu.kingconquest.framework.utils.Location;

public class Crate extends Entity {

    public Crate(Location location) {
        super(location, SokobanEntityType.CRATE);
    }
}
