package eu.kingconquest.Entities;

import eu.kingconquest.Core.GameController;
import eu.kingconquest.Core.Tile;
import eu.kingconquest.Utils.Location;

import java.awt.*;

public abstract class Entity{
    private Location location;
    private Image icon;

    public Entity(Location location, String imagePath) {
        this.location = location;
        setIcon(imagePath);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location.setX(x);
        location.setY(y);
    }
    public void setLocation(Location location) {
        this.location = location;
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