package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CrateTest {

    @Test
    public void crateConstructorTest() {
        // Arrange
        Location location = new Location(1, 1); // assuming Location takes two integers

        // Act
        Crate crate = new Crate(location);

        // Assert
        assertEquals(location, crate.getLocation());
        assertEquals(SokobanEntityIcon.CRATE, crate.getEntityType());
    }

    @Test
    public void crateSetLocationTest() {
        // Arrange
        Location location = new Location(1, 1);
        Location newLocation = new Location(2, 2);
        Crate crate = new Crate(location);

        // Act
        crate.setLocation(newLocation);

        // Assert
        assertEquals(newLocation, crate.getLocation());
    }

    @Test
    public void crateSetLocationNullTest() {
        // Arrange
        Crate crate = new Crate(new Location(1, 1));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> crate.setLocation(null));
    }

    @Test
    public void crateEqualsTest() {
        // Arrange
        Location location = new Location(1, 1);
        Crate crate1 = new Crate(location);
        Crate crate2 = new Crate(location);

        // Act & Assert
        assertTrue(crate1.equals(crate2));
    }
}
