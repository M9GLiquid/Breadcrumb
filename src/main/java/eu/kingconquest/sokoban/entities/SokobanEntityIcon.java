package eu.kingconquest.sokoban.entities;

import eu.kingconquest.framework.entity.EntityIcon;

import java.awt.*;

public enum SokobanEntityIcon implements EntityIcon {
    WALL("wall.png", "#"),
    PLAYER("player.png", "P"),
    GROUND("ground.png", "-"),
    GROUND_MARKED("ground-marked.png", "X"),
    CRATE("crate.png", "C"),
    CRATE_MARKED("crate-marked.png", "M");

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
