package eu.kingconquest.framework.entities;

import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.utils.Location;

import java.awt.*;
import java.io.Serializable;

/**
 * Represents an abstract Entity class that serves as a base for various game objects.
 * This class is responsible for managing the location and type of the entities, as well as
 * drawing the entities on the screen.
 */
public abstract class Entity implements Serializable {
    private Location location;
    private EntityIcon entityIcon;

    public Entity() {}

    /**
     * Creates an Entity with the specified location and EntityType.
     *
     * @param location   the initial location of the entities
     * @param entityIcon the type of the entities
     */
    public Entity(Location location, EntityIcon entityIcon) {
        setLocation(location);
        setEntityType(entityIcon);
    }

    /**
     * Gets the current location of the entities.
     *
     * @return the current location of the entities
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the entities.
     *
     * @param location the new location of the entities
     * @throws IllegalArgumentException if the location is null
     */
    public void setLocation(Location location) {
        if (location == null)
            throw new IllegalArgumentException("Location cannot be null");
        this.location = location;
    }

    /**
     * Gets the EntityType of the entities.
     *
     * @return the EntityType of the entities
     */
    public EntityIcon getEntityType() {
        return entityIcon;
    }

    /**
     * Sets the EntityType of the entities.
     *
     * @param entityIcon the new EntityType of the entities
     * @throws IllegalArgumentException if the entityType is null
     */
    public void setEntityType(EntityIcon entityIcon) {
        if (entityIcon == null)
            throw new IllegalArgumentException("EntityType cannot be null");
        this.entityIcon = entityIcon;
    }

    /**
     * Compares the specified Entity object with this Entity for equality.
     * The comparison is based on the location and EntityType of the Entities.
     *
     * @param entity the Entity to be compared for equality with this Entity
     * @return true if the specified Entity is equal to this Entity; false otherwise
     */
    public boolean equals(Entity entity){
        return location.equals(entity.getLocation())
                && entityIcon.equals(entity.getEntityType());
    }

    /**
     * Draws the entities on the screen using the specified Graphics context.
     *
     * @param g the Graphics context to draw the entities on
     */
    public void draw(Graphics g) {
        g.drawImage(entityIcon.getIcon(),
                location.getX() * Tile.getTileSize(),
                location.getY() * Tile.getTileSize(),
                Tile.getTileSize(),
                Tile.getTileSize(),
                null);
    }
}