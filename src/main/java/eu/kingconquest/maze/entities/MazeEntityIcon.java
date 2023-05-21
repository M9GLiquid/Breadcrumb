package eu.kingconquest.maze.entities;

import eu.kingconquest.framework.entity.EntityIcon;
import java.awt.*;

public enum MazeEntityIcon implements EntityIcon {
    WALL("maze/images/wall.png", "#");

    MazeEntityIcon(String imagePath, String symbol) {
    }

    @Override
    public Image getIcon() {
        return null;
    }
}
