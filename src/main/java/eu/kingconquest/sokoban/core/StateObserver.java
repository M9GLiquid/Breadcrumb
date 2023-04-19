package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.Game;
import eu.kingconquest.framework.GameObserver;

public class StateObserver implements GameObserver {
    private final Game game;


    public StateObserver(Game game){
        this.game = game;
    }

    @Override
    public void update() {
        if (!(game instanceof Sokoban sokoban)) {
            System.err.println("game is not of Sokoban DataType");
            return;
        }

        switch (sokoban.getState()) {
            case WIN, GAME_OVER -> sokoban.gameOver();
            case INITIATING -> sokoban.initiate();
            case LEVEL_COMPLETE -> sokoban.start();
            case RUNNING -> {}
            case RESETTING -> sokoban.reset();
        }
    }
}
