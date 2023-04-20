package eu.kingconquest.framework.entity;

import eu.kingconquest.framework.Tile;
import eu.kingconquest.framework.utils.Location;

import java.awt.*;

/**
 * Represents an abstract Entity class that serves as a base for various game objects.
 * This class is responsible for managing the location and type of the entity, as well as
 * drawing the entity on the screen.
 */
public abstract class Entity {
    private Location location;
    private EntityType entityType;

    /**
     * Creates an Entity with the specified location and EntityType.
     *
     * @param location   the initial location of the entity
     * @param entityType the type of the entity
     */
    public Entity(Location location, EntityType entityType) {
        setLocation(location);
        setEntityType(entityType);
    }

    public Entity() {

    }

    /**
     * Gets the current location of the entity.
     *
     * @return the current location of the entity
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the entity.
     *
     * @param location the new location of the entity
     * @throws IllegalArgumentException if the location is null
     */
    public void setLocation(Location location) {
        if (location == null)
            throw new IllegalArgumentException("Location cannot be null");
        this.location = location;
    }

    /**
     * Gets the EntityType of the entity.
     *
     * @return the EntityType of the entity
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the EntityType of the entity.
     *
     * @param entityType the new EntityType of the entity
     * @throws IllegalArgumentException if the entityType is null
     */
    public void setEntityType(EntityType entityType) {
        if (entityType == null)
            throw new IllegalArgumentException("EntityType cannot be null");
        this.entityType = entityType;
    }

    /**
     * Draws the entity on the screen using the specified Graphics context.
     *
     * @param g the Graphics context to draw the entity on
     */
    public void draw(Graphics g) {
        g.drawImage(entityType.getIcon(),
                location.getX() * Tile.TILE_SIZE,
                location.getY() * Tile.TILE_SIZE,
                Tile.TILE_SIZE,
                Tile.TILE_SIZE,
                null);
    }
}