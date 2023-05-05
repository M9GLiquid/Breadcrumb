package eu.kingconquest.framework.utils;

import java.io.Serializable;

/**
 * The Location class represents a 2D coordinate in the game world.
 * It provides methods for getting and setting the x and y coordinates,
 * modifying the location, calculating the distance between locations, and comparing locations.
 */
public class Location implements Serializable {
    private int x;
    private int y;

    /**
     * Constructs a new Location instance with the given x and y coordinates.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the location.
     *
     * @return The x-coordinate of the location.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the location.
     *
     * @return The y-coordinate of the location.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the location.
     *
     * @param y The new y-coordinate of the location.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the x-coordinate of the location.
     *
     * @param x The new x-coordinate of the location.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the x and y coordinates of the location based on another location.
     *
     * @param location The location whose coordinates should be used to update this location.
     */
    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    /**
     * Adds the x and y coordinates of another location to this location and returns a new Location instance.
     *
     * @param other The location to add to this location.
     * @return A new Location instance representing the sum of the two locations' coordinates.
     */
    public Location add(Location other) {
        return new Location(this.getX() + other.getX(), this.getY() + other.getY());
    }


    /**
     * Calculates the Manhattan distance between this location and another location.
     *
     * @param location The other location to calculate the distance from.
     * @return The Manhattan distance between this location and the specified location.
     */
    public int getDistance(Location location) {
        return Math.abs(location.getX() - x) + Math.abs(location.getY() - y);
    }

    /**
     * Returns a string representation of the location.
     *
     * @return A string representation of the location in the format "Location{x=..., y=...}".
     */
    @Override
    public String toString() {
        return "Location{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * Determines whether this location is equal to another object.
     * Two locations are considered equal if they have the same x and y coordinates.
     *
     * @param o The object to compare with this location.
     * @return True if the object is a Location instance with the same x and y coordinates, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        return (x == location.x && y == location.y);
    }
}
