package framework;

import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LocationFrameworkTest {
    private Location location;

    @BeforeEach
    public void setUp() {
        location = new Location(5, 7);
    }

    @Test
    public void testGetX() {
        assertEquals(5, location.getX(), "X coordinate should be 5");
    }

    @Test
    public void testGetY() {
        assertEquals(7, location.getY(), "Y coordinate should be 7");
    }

    @Test
    public void testSetX() {
        location.setX(10);
        assertEquals(10, location.getX(), "X coordinate should be updated to 10");
    }

    @Test
    public void testSetY() {
        location.setY(15);
        assertEquals(15, location.getY(), "Y coordinate should be updated to 15");
    }

    @Test
    public void testSetLocation() {
        Location newLocation = new Location(2, 3);
        location.setLocation(newLocation);
        assertEquals(newLocation, location, "Location should be updated");
    }

    @Test
    public void testAdd() {
        Location other = new Location(3, 4);
        Location result = location.add(other);
        Location expected = new Location(8, 11);
        assertEquals(expected, result, "Locations should be added correctly");
    }

    @Test
    public void testGetDistance() {
        Location other = new Location(10, 12);
        int distance = location.getDistance(other);
        assertEquals(10, distance, "Manhattan distance should be 10");
    }

    @Test
    public void testEquals() {
        Location sameLocation = new Location(5, 7);
        assertEquals(location, sameLocation, "Locations should be equal");
    }

    @Test
    public void testNotEquals() {
        Location differentLocation = new Location(1, 1);
        assertNotEquals(location, differentLocation, "Locations should not be equal");
    }
}
