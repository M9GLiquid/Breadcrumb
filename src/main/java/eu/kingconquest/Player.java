package eu.kingconquest;

import java.awt.*;

public class Player implements Element{
    private static final Image icon = GameController.loadImage("player.png");
    private final Location location;

    public Player(int x, int y){
        location = new Location(x, y);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(int x, int y) {
        location.setX(x);
        location.setY(y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(icon, location.getX() * Tile.TILE_SIZE, location.getY() * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
    }
}
