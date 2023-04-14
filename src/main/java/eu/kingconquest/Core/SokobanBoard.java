package eu.kingconquest.Core;

public class SokobanBoard extends GameBoard {

    public SokobanBoard(int numRows, int numCols) {
        super(numRows, numCols);
    }

    @Override
    public boolean isMoveValid(int row, int col) {
        return false;
    }

    @Override
    public void makeMove(int row, int col) {

    }
}
