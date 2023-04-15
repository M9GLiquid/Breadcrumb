package eu.kingconquest.Entities;

import eu.kingconquest.Utils.Location;

public class Crate extends Entity {
    private boolean isMarked;

    public Crate(Location location, boolean isMarked) {
        super(location, "crate.png");
        this.isMarked = isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void toggleIcon() {
        if (isMarked)
            setIcon("crate.png");
        else
            setIcon("crate-marked.png");
        isMarked = !isMarked;
    }
}
