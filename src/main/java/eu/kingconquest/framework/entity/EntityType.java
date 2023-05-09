package eu.kingconquest.framework.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public interface EntityType {

    /**
     * Gets the icon of the EntityType.
     *
     * @return the icon image of the EntityType
     */
    Image getIcon();

    static Image loadImage(String imageName) {
        try (InputStream inputStream = EntityType.class.getClassLoader().getResourceAsStream("images/" + imageName)) {
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
