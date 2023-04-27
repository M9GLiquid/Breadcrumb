package eu.kingconquest.framework;

import eu.kingconquest.framework.utils.Location;

import java.io.Serializable;

/**
 * The GameBoard class is an abstract base class for various game board implementations.
 * Each GameBoard contains a grid of Tiles, the number of rows, and the number of columns.
 * It provides methods for creating a GameBoard, checking if a move is valid, and making a move.
 */
public abstract class GameBoard implements Serializable {
    public int COLS = 30;
    public int ROWS = 20;
    public Tile[][] grid;

    /**
     * Creates a GameBoard with the specified number of rows and columns.
     */
    public GameBoard(Integer numRows, Integer numCols) {
        if (numRows != null)
            ROWS = numRows;
        if (numCols != null)
            COLS = numCols;

        grid = new Tile[COLS][ROWS];
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




