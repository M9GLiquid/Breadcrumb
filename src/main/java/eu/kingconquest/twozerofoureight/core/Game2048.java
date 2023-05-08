package eu.kingconquest.twozerofoureight.core;

import eu.kingconquest.framework.controllers.GameController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.observers.StateObserver;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.utils.Tile;

public class Game2048 extends Game {
    public Game2048(GameFrame gameframe) {
        super(gameframe,"2048");

        // Game Board setup
        setBoard(new Board2048(this, null, null));
        getBoard().setState(GameState.RUNNING);

        // Game Controller setup
        setController(new GameController(getBoard()));

        // Key Listener setup
        getGameFrame().addKeyListener(getController());

        // Game View setup
        getGameFrame().addView(new StartMenu(this), 970, 640);

        // Game Observers Setup
        getController().addObserver(new StateObserver(this));
        getController().notifyObservers();

        Tile.setTileSize(64);
    }

    @Override
    public void initiate() {
        getBoard().setState(GameState.INITIATING);
    }

    @Override
    public void gameOver() {
        getBoard().setState(GameState.GAME_OVER);
    }

    @Override
    public void start() {
    }

    @Override
    public void reset() {

    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public void pause() {

    }
}
