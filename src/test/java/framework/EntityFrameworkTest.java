package framework;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityType;
import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the Entity framework, specifically testing the Entity and EntityType classes.
 * This class includes tests for constructors, setLocation, setEntityType, and loadImage methods.
 */
public class EntityFrameworkTest {

    // A concrete subclass of Entity for testing purposes
    private static class TestEntity extends Entity {
        TestEntity(Location location, EntityType entityType) {
            super(location, entityType);
        }
    }

    /**
     * Test the setLocation method with a null parameter.
     */
    @Test
    public void testSetLocation_nullParameter() {
        TestEntity testEntity = new TestEntity(new Location(0, 0), new EntityType("crate.png") {});
        assertThrows(IllegalArgumentException.class, () -> testEntity.setLocation(null));
    }

    /**
     * Test the setEntityType method with a null parameter.
     */
    @Test
    public void testSetEntityType_nullParameter() {
        TestEntity testEntity = new TestEntity(new Location(0, 0), new EntityType("crate.png") {});
        assertThrows(IllegalArgumentException.class, () -> testEntity.setEntityType(null));
    }

    /**
     * Test the Entity constructor with null parameters.
     */
    @Test
    public void testEntityConstructor_nullParameters() {
        EntityType testEntityType = new EntityType("crate.png") {};
        assertThrows(IllegalArgumentException.class, () -> new TestEntity(null, testEntityType));
        assertThrows(IllegalArgumentException.class, () -> new TestEntity(new Location(1, 2), null));
    }

    /**
     * Test the loadImage method with a non-existent image.
     */
    @Test
    public void testLoadImage_nonExistentImage() {
        EntityType testEntityType = new EntityType("non_existent_image.png") {};
        assertNull(testEntityType.getIcon());
    }

    /**
     * Test the loadImage method with an existing image.
     */
    @Test
    public void testLoadImage_existingImage() {
        EntityType testEntityType = new EntityType("crate.png") {};
        Image icon = testEntityType.getIcon();
        assertNotNull(icon);
    }
}
