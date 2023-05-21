package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.entities.EntityIcon;

import java.awt.*;

public enum SokobanEntityIcon implements EntityIcon {
    WALL("sokoban/images/wall.png", "#"),
    PLAYER("sokoban/images/player.png", "P"),
    GROUND("sokoban/images/ground.png", "-"),
    GROUND_MARKED("sokoban/images/ground-marked.png", "X"),
    CRATE("sokoban/images/crate.png", "C"),
    CRATE_MARKED("sokoban/images/crate-marked.png", "M");

    private final Image icon;
    private final String symbol;

    SokobanEntityIcon(String imagePath, String symbol) {
        icon = EntityIcon.loadImage(imagePath);
        this.symbol = symbol;
    }

    @Override
    public Image getIcon() {
        return icon;
    }

    @Override
    public String toString(){
        return symbol;
    }
}
