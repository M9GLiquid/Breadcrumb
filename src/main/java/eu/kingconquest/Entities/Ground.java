package eu.kingconquest.Entities;

import eu.kingconquest.Core.Tile;
import eu.kingconquest.Core.EntityType;
import eu.kingconquest.Utils.Location;

public class Ground extends Tile {

    public Ground(Location location){
        super(location, EntityType.GROUND, true);
    }
}
