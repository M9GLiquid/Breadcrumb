package eu.kingconquest.framework.core;

import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.ui.GameFrame;

import java.awt.event.KeyListener;

/**
 * The Game class is an abstract base class for various game implementations.
 * Each Game contains a GameBoard, GameController, GameState, GamePanel, and a list of entities.
 * It provides methods for initiating, starting, resetting, and generating levels for the game,
 * as well as checking if the game is finished and accessing game components.
 */
public abstract class Game {
    private final String title;
    private GameBoard board;
    private GameStrategy controller;
    private final GameFrame gameFrame;

    private GameData gameData;

    protected Game(GameFrame gameFrame, String title) {
        this.gameFrame = gameFrame;
        this.title = title;
    }

    /**
     * Initializes the game by setting up necessary objects and configurations.
     */
    public abstract void initiate();
    /**
     * Starts the game, handling game logic and progression.
     */
    public abstract void start();
    public abstract void gameOver();

    /**
     * Resets the game to its initial state.
     */
    public abstract void restart();

    public abstract void save();
    public abstract void load();

    public abstract void pause();

    /**
     * Gets the GameBoard associated with the game.
     *
     * @return the GameBoard object
     */
    public GameBoard getBoard() {
        return board;
    }



    /**
     * Gets the GameController of the game.
     *
     * @return A GameController Object
     */
    public GameStrategy getController() {
        return controller;
    }

    /**
     * Gets the Title of the game.
     *
     * @return A String with the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the GameFrame of the game.
     *
     * @return A GameFrame Object
     */
    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public void setController(GameStrategy controller) {
        this.controller = controller;
    }

    protected void setDesiredController(GameStrategy controller) {
        // Remove all listeners if there are any
        for (KeyListener keyListener : getGameFrame().getKeyListeners())
            getGameFrame().removeKeyListener(keyListener);

        setController(controller);
        getGameFrame().addKeyListener(getController());
    }
}
