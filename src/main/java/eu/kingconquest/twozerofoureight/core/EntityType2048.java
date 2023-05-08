package eu.kingconquest.twozerofoureight.core;

import eu.kingconquest.framework.entity.EntityType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public enum EntityType2048 implements EntityType {
    E2("tile_1.png"),
    E4("tile_2.png"),
    E8("tile_3.png"),
    E16("tile_4.png"),
    E32("tile_5.png"),
    E64("tile_6.png"),
    E128("tile_7.png"),
    E256("tile_8.png"),
    E512("tile_9.png"),
    E1024("tile_10.png"),
    E2048("tile_11.png"),
    E4096("tile_12.png"),
    E8192("tile_13.png"),
    E16384("tile_14.png"),
    WALL("wall.png");
    private final Image icon;

    EntityType2048(String imagePath) {
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

