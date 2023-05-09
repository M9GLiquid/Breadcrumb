package eu.kingconquest.framework.observers;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.models.GameBoard;

public class StateObserver implements GameStateObserver {
    private final Game game;

    public StateObserver(Game game){
        this.game = game;
    }

    @Override
    public void update() {
        GameBoard board = game.getBoard();
        switch (board.getState()) {
            case WIN, GAME_OVER -> game.gameOver();
            case INITIATING -> game.initiate();
            case LEVEL_COMPLETE -> game.start();
            case RUNNING -> {}
            case RESETTING -> game.reset();
            case PAUSED -> game.pause();
        }
    }
}
