package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void playerConstructorTest() {
        // Arrange
        Location location = new Location(1, 1); // assuming Location takes two integers

        // Act
        Player player = new Player(location);

        // Assert
        assertEquals(location, player.getLocation());
        assertEquals(SokobanEntityIcon.PLAYER, player.getEntityType());
    }

    @Test
    public void playerSetLocationTest() {
        // Arrange
        Location location = new Location(1, 1);
        Location newLocation = new Location(2, 2);
        Player player = new Player(location);

        // Act
        player.setLocation(newLocation);

        // Assert
        assertEquals(newLocation, player.getLocation());
    }

    @Test
    public void playerSetLocationNullTest() {
        // Arrange
        Player player = new Player(new Location(1, 1));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> player.setLocation(null));
    }

    @Test
    public void playerEqualsTest() {
        // Arrange
        Location location = new Location(1, 1);
        Player player1 = new Player(location);
        Player player2 = new Player(location);

        // Act & Assert
        assertTrue(player1.equals(player2));
    }
}
