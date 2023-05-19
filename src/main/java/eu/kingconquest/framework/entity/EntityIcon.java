package eu.kingconquest.framework.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * The EntityIcon interface provides a mechanism for entities to specify their graphical representation.
 * This interface defines methods for retrieving the image used as the entity's icon,
 * and a static method for loading images from a specified path.
 */
public interface EntityIcon {

    /**
     * Gets the icon of the EntityType.
     *
     * @return the icon image of the EntityType
     */
    Image getIcon();

    /**
     * Loads an image resource from the "images/" directory.
     * It will return null and print an error message if the image was not found or an error occurred during reading.
     *
     * @param imageName the name of the image file to load
     * @return the loaded image, or null if the image could not be found or read
     */
    static Image loadImage(String imageName) {
        try (InputStream inputStream = EntityIcon.class.getClassLoader().getResourceAsStream("sokoban/images/" + imageName)) {
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
