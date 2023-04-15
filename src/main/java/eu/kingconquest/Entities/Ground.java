package eu.kingconquest.Entities;

import eu.kingconquest.Core.Tile;
import eu.kingconquest.Utils.Location;

public class Ground extends Tile {
    private boolean isMarked = false;

    public Ground(Location location){
        super(location, "ground.png", true);
    }

    public boolean isMarked(){
        return isMarked;
    }

    public void toggleIcon(){
        if (isMarked)
            setIcon("ground.png");
        else
            setIcon("ground-marked.png");
        isMarked = !isMarked;
    }
}
