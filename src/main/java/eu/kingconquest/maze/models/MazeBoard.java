package eu.kingconquest.maze.models;

import eu.kingconquest.framework.entity.EntityIcon;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.maze.core.Maze;

public class MazeBoard extends GameBoard {
    private final Maze game;
    public EntityIcon lastEntityIconMoved = null;

    public MazeBoard(Maze game, Integer numRows, Integer numCols){
        super(numRows, numCols);
        this.game = game;

    }

    @Override
    protected boolean isMoveInvalid(Location location) {
        return false;
    }

    @Override
    public void makeMove(Location direction) {

    }
}
