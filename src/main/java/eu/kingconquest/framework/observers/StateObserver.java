package eu.kingconquest.framework.observers;

import eu.kingconquest.framework.core.Game;

/**
 * The StateObserver class is responsible for observing the game state and performing
 * actions based on state changes. It implements the GameStateObserver interface.
 */
public class StateObserver implements GameObserver {
    private final Game game;

    /**
     * Creates a new StateObserver for the specified Game.
     *
     * @param game the Game this observer will monitor
     */
    public StateObserver(Game game){
        this.game = game;
    }

    @Override
    public void update() {
        switch (game.getBoard().getState()) {
            case WIN, GAME_OVER -> game.gameOver();
            case INITIATING -> game.initiate();
            case LEVEL_COMPLETE -> game.start();
            case RUNNING -> {}
            case RESETTING -> game.restart();
            case PAUSED -> game.pause();
        }
    }
}
