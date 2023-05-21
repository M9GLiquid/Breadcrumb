package eu.kingconquest.twozerofoureight.core;

import eu.kingconquest.framework.entities.EntityIcon;

import java.awt.*;

/**
 * Enumeration of entities types in the 2048 game, implementing the EntityIcon interface.
 */
public enum EntityIcon2048 implements EntityIcon {
    E2("game2048/images/tile_1.png"),
    E4("game2048/images/tile_2.png"),
    E8("game2048/images/tile_3.png"),
    E16("game2048/images/tile_4.png"),
    E32("game2048/images/tile_5.png"),
    E64("game2048/images/tile_6.png"),
    E128("game2048/images/tile_7.png"),
    E256("game2048/images/tile_8.png"),
    E512("game2048/images/tile_9.png"),
    E1024("game2048/images/tile_10.png"),
    E2048("game2048/images/tile_11.png"),
    E4096("game2048/images/tile_12.png"),
    E8192("game2048/images/tile_13.png"),
    E16384("game2048/images/tile_14.png"),
    WALL("game2048/images/wall.png"),
    GROUND("game2048/images/ground.png");
    private final Image icon;

    /**
     * Constructs an EntityType2048 with the specified image path.
     * @param imagePath imagePath The path to the icon image.
     */
    EntityIcon2048(String imagePath) {
        icon = EntityIcon.loadImage(imagePath);
    }

    /**
     * Returns the icon image associated with this entities type.
     * @return The icon image.
     */
    @Override
    public Image getIcon() {
        return icon;
    }
}

