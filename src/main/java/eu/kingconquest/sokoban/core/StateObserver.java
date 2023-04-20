package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.GameObserver;

public class StateObserver implements GameObserver {
    private final Sokoban game;


    public StateObserver(Sokoban game){
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
