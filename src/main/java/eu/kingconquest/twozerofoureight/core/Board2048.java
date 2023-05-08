package eu.kingconquest.twozerofoureight.core;


import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;


public class Board2048 extends GameBoard {
    private final Game2048 game;

    public Board2048(Game2048 game, Integer numRows, Integer numCols) {
        super(numRows, numCols);
        this.game = game;
    }

    @Override
    protected boolean isMoveInvalid(Location location) {
        return true;
    }

    public boolean isMatch(Location location1, Location location2){
        return grid[location1.getX()][location1.getY()] == grid[location2.getX()][location2.getY()];
    }

    @Override
    public void makeMove(Location direction) {
        if(!isMoveInvalid(direction))
            game.getBoard().setState(GameState.GAME_OVER);

    }
}
