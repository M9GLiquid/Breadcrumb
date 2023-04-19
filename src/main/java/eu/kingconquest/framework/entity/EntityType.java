package eu.kingconquest.framework.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents an abstract EntityType class that serves as a base for different entity types.
 * Each EntityType contains an icon represented by an Image object.
 */
public abstract class EntityType {
    private final Image icon;

    /**
     * Creates an EntityType with the specified image path.
     *
     * @param imagePath the path to the image file for this EntityType
     */
    protected EntityType(String imagePath) {
        this.icon = loadImage(imagePath);
    }

    /**
     * Gets the icon of the EntityType.
     *
     * @return the icon image of the EntityType
     */
    public Image getIcon() {
        return icon;
    }

    /**
     * Loads an image from the specified image name.
     *
     * @param imageName the name of the image file to be loaded
     * @return the loaded Image object, or null if the image could not be found or loaded
     */
    private static Image loadImage(String imageName) {
        try (InputStream inputStream = EntityType.class.getClassLoader().getResourceAsStream(imageName)) {
            if (inputStream == null) {
                System.err.println("Image not found: " + imageName);
                return null;
            }
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
