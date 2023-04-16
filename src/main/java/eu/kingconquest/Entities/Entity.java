package eu.kingconquest.Entities;

import eu.kingconquest.Core.EntityType;
import eu.kingconquest.Core.Tile;
import eu.kingconquest.Utils.Location;

import java.awt.*;

public abstract class Entity{
    private Location location;
    private EntityType entityType;

    public Entity(Location location, EntityType entityType) {
        this.location = location;
        this.entityType = entityType;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public EntityType getEntityType(){
        return entityType;
    }

    public void setEntityType(EntityType entityType){
        this.entityType = entityType;
    }

    public void draw(Graphics g) {
        g.drawImage(entityType.getIcon(), location.getX() * Tile.TILE_SIZE, location.getY() * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
    }
}