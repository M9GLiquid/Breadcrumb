package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.entity.EntityType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public enum SokobanEntityType implements EntityType {
    WALL("wall.png"),
    PLAYER("player.png"),
    GROUND("ground.png"),
    GROUND_MARKED("ground-marked.png"),
    CRATE("crate.png"),
    CRATE_MARKED("crate-marked.png");

    private final Image icon;

    SokobanEntityType(String imagePath) {
        icon = loadImage(imagePath);
    }

    @Override
    public Image getIcon() {
        return icon;
    }

    private static Image loadImage(String imageName) {
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
