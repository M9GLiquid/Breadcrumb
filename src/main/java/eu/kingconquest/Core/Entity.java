package eu.kingconquest.Core;

import eu.kingconquest.Utils.Location;

import java.awt.*;

public abstract class Entity{
    private final Location location;
    private Image icon;

    public Entity(int x, int y, String imagePath) {
        location = new Location(x, y);
        setIcon(imagePath);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location.setX(x);
        location.setY(y);
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(String imagePath) {
        this.icon = GameController.loadImage(imagePath);
    }

    public void draw(Graphics g) {
        g.drawImage(icon, location.getX() * Tile.TILE_SIZE, location.getY() * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
    }
}