package eu.kingconquest;

public abstract class GameBoard {
    protected int[][] grid;
    protected int numRows;
    protected int numCols;

    public GameBoard(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        grid = new int[numRows][numCols];
    }

    public abstract boolean isMoveValid(int row, int col);
    public abstract void makeMove(int row, int col);
}
