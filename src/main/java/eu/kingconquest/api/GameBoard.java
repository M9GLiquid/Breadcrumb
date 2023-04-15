package eu.kingconquest.api;

import eu.kingconquest.Core.Tile;
import eu.kingconquest.Utils.Location;

public abstract class GameBoard {
    public Tile[][] grid;
    public int numRows;
    public int numCols;

    public GameBoard(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        grid = new Tile[numRows][numCols];
    }

    public abstract boolean isMoveValid(Location location);
    public abstract void makeMove(Location location);
}
