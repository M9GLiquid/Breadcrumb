package eu.kingconquest.framework;

import eu.kingconquest.framework.utils.Location;

/**
 * Represents an abstract GameBoard class that serves as a base for different game board implementations.
 * Each GameBoard contains a grid of Tiles and the number of rows and columns.
 */
public abstract class GameBoard {
    public static int ROWS = 30;
    public static int COLS = 20;
    public Tile[][] grid;

    /**
     * Creates a GameBoard with the specified number of rows and columns.
     */
    public GameBoard(Integer numRows, Integer numCols) {
        if (numRows == null || numCols == null) {
            grid = new Tile[ROWS][COLS];
        } else {
            ROWS = numRows;
            COLS = numCols;
        }
    }

    /**
     * Checks if a move to the specified location is valid.
     *
     * @param location the location to be checked
     * @return true if the move is valid, false otherwise
     */
    protected abstract boolean isMoveValid(Location location);

    /**
     * Makes a move on the game board in the specified direction.
     * The implementation should include game-specific move logic.
     *
     * @param direction the direction in which the move should be made
     */
    public abstract void makeMove(Location direction);
}
