package eu.kingconquest.framework.observers;

import eu.kingconquest.framework.models.GameBoard;

public class ConsoleViewObserver implements GameViewObserver {

    private final GameBoard board;

    public ConsoleViewObserver(GameBoard board){
        this.board = board;
    }
    @Override
    public void update() {
        render();
    }

    private void render() {
        System.out.println(board);
    }
}
