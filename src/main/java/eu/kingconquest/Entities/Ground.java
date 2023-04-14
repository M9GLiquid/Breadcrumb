package eu.kingconquest.Entities;

import eu.kingconquest.Core.Tile;

public class Ground extends Tile {
    private boolean isMarked = false;

    public Ground(int x, int y){
        super(x, y, "player.png", true);
    }

    public boolean isMarked(){
        return isMarked;
    }

    public void toggleIcon(){
        if (isMarked)
            setIcon("crate.png");
        else
            setIcon("crate-marked.png");
        isMarked = !isMarked;
    }
}
