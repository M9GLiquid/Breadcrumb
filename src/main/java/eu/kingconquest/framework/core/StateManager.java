package eu.kingconquest.framework.core;

import eu.kingconquest.framework.observers.GameObserver;

/**
 * The StateObserver class is responsible for observing the game state and performing
 * actions based on state changes. It implements the GameStateObserver interface.
 */
public class StateManager implements GameObserver {
    private final Game game;

    /**
     * Creates a new StateObserver for the specified Game.
     *
     * @param game the Game this observer will monitor
     */
    public StateManager(Game game){
        this.game = game;
    }

    @Override
    public void update() {
        switch (game.getBoard().getState()) {
            case WIN, GAME_OVER -> game.end();
            case INITIATING -> game.initiate();
            case LEVEL_COMPLETE -> game.start();
            case RUNNING -> {}
            case RESETTING -> game.restart();
            case PAUSED -> game.pause();
        }
    }
}
