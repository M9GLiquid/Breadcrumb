package eu.kingconquest.framework.observers;

import eu.kingconquest.framework.models.GameBoard;

/**
 * Represents an observer that prints the state of the game board to the console
 * every time the game state changes.
 */
public class ConsoleViewObserver implements GameViewObserver {

    private final GameBoard board;

    /**
     * Constructs a new ConsoleViewObserver that observes the specified GameBoard.
     *
     * @param board the GameBoard to be observed
     */
    public ConsoleViewObserver(GameBoard board){
        this.board = board;
    }

    @Override
    public void update() {
        System.out.println(board);
    }
}
