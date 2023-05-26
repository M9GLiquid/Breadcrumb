package eu.kingconquest.maze.entities;

import eu.kingconquest.framework.entity.EntityIcon;
import java.awt.*;

public enum MazeEntityIcon implements EntityIcon {
     MAZE_WALL("maze/images/maze-wall.png", "#"),
    MAZE_PLAYER("maze/images/maze-player.png", "P"),
    MAZE_GROUND("maze/images/maze-ground.png", "-"),
    MAZE_GROUND_MARKED("maze/images/marker.png", "X");

    private final Image icon;
    private final String symbol;

    MazeEntityIcon(String imagePath, String symbol) {
        icon = EntityIcon.loadImage(imagePath);
        this.symbol = symbol;
    }

    @Override
    public Image getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
