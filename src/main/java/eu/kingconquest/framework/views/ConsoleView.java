package eu.kingconquest.framework.views;

import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.observers.GameObserver;

/**
 * Represents an observer that prints the state of the game board to the console
 * every time the game state changes.
 */
public class ConsoleView implements GameObserver {

    private final GameBoard board;

    /**
     * Constructs a new ConsoleViewObserver that observes the specified GameBoard.
     *
     * @param board the GameBoard to be observed
     */
    public ConsoleView(GameBoard board){
        this.board = board;
    }

    @Override
    public void update() {
        System.out.println(board);
    }
}
