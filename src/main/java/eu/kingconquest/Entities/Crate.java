package eu.kingconquest.Entities;

import eu.kingconquest.Core.Entity;

public class Crate extends Entity {
    private boolean isMarked = false;

    public Crate(int x, int y){
        super(x, y, "Crate");
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
