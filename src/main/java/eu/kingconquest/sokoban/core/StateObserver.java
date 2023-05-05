package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameObserver;

public class StateObserver implements GameObserver {
    private final Game game;

    public StateObserver(Game game){
        this.game = game;
    }

    @Override
    public void update() {
        switch (game.getState()) {
            case WIN, GAME_OVER -> game.gameOver();
            case INITIATING -> game.initiate();
            case LEVEL_COMPLETE -> game.start();
            case RUNNING -> {}
            case RESETTING -> game.reset();
        }
    }
}
