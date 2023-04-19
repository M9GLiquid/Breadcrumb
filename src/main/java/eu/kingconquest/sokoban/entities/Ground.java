package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.Tile;
import eu.kingconquest.sokoban.core.SokobanEntityType;
import eu.kingconquest.framework.utils.Location;

public class Ground extends Tile {

    public Ground(Location location){
        super(location, SokobanEntityType.GROUND, true);
    }
}
