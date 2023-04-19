package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.entity.EntityType;

public class SokobanEntityType extends EntityType {
    public static final SokobanEntityType WALL = new SokobanEntityType("wall.png");
    public static final SokobanEntityType PLAYER = new SokobanEntityType("player.png");
    public static final SokobanEntityType GROUND = new SokobanEntityType("ground.png");
    public static final SokobanEntityType GROUND_MARKED = new SokobanEntityType("ground-marked.png");
    public static final SokobanEntityType CRATE = new SokobanEntityType("crate.png");
    public static final SokobanEntityType CRATE_MARKED = new SokobanEntityType("crate-marked.png");

    private SokobanEntityType(String imagePath) {
        super(imagePath);
    }
}
