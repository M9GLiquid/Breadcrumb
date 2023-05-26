package eu.kingconquest.maze.models;

import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.maze.core.Maze;


public class MazeBoard extends GameBoard {
    private final Maze game;

    public MazeBoard(Maze game, Integer numRows, Integer numCols){
        super(numRows, numCols);
        this.game = game;

    }

    @Override
    protected boolean isMoveInvalid(Location location) {
        //Not able to walk through the wall
        return !(grid[location.getY()][location.getX()].isWalkable());
    }

    @Override
    public void makeMove(Location direction) {
    }



    protected boolean MazeIsLevelComplete() {
        long count = game.getGoals().stream()
                .filter(goal -> game.getGoals().stream()
                        .anyMatch(marker -> marker.getLocation().equals(goal.getLocation())))
                .count();

        return count == game.getGoals().size();
    }

}
