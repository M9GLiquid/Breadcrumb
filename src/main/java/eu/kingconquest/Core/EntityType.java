package eu.kingconquest.Core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public enum EntityType {
    WALL("wall.png"),
    PLAYER("player.png"),
    GROUND("ground.png"),
    GROUND_MARKED("ground-marked.png"),
    CRATE("crate.png"),
    CRATE_MARKED("crate-marked.png");
    private final Image icon;

    EntityType(String imagePath) {
        this.icon = loadImage(imagePath);
    }

    public Image getIcon() {
        return icon;
    }

    public static Image loadImage(String imageName) {
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
