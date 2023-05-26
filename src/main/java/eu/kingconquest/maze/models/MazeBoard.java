package eu.kingconquest.maze.models;

import eu.kingconquest.framework.core.GameState;
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
        Location newPlayerLocation = game.getPlayer().getLocation();
        newPlayerLocation = newPlayerLocation.add(direction);

        if (isMoveInvalid(newPlayerLocation))
            return;

        game.getPlayer().setLocation(newPlayerLocation);

        if (isMazeLevelComplete())
            setState(GameState.LEVEL_COMPLETE);

        // set the new location for the player
        game.getController().clearAudioObservers();
    }



    protected boolean isMazeLevelComplete() {
        return (game.getPlayer().getLocation().equals(game.getGoal().getLocation()));
    }

}
