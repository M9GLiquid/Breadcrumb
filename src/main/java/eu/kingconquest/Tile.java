package eu.kingconquest;

import java.awt.*;

public class Tile {
    public static final int TILE_SIZE = 32;
    private final Location location;
    private final boolean isWalkable;

    private static final Image wallImage = GameController.loadImage("wall.png");
    private static final Image floorImage = GameController.loadImage("grass.png");

    public Tile(Location location, boolean isWalkable) {
        this.location = location;

        this.isWalkable = isWalkable;
    }

    public Location getLocation(){
        return location;
    }

    public void draw(Graphics g) {
        Image image = isWalkable ? floorImage : wallImage;
        g.drawImage(image, location.getX() * TILE_SIZE, location.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
