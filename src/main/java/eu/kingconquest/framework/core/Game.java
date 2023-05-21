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

    /**
     * Constructs a new Game instance with the specified GameFrame and title.
     *
     * @param gameFrame the GameFrame object representing the game's GUI
     * @param title the title of the game
     */
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

    /**
     * Resets the game to its initial state.
     */
    public abstract void restart();

    /**
     * Handles the action that happens when you pause the game,
     * pause is handled by the default controller by [esc] - buttons
     */
    public abstract void pause();

    /**
     * Saves the current state of the game.
     */
    public abstract void save();

    /**
     * Loads a previously saved state of the game.
     */
    public abstract void load();

    /**
     * Defines the behavior of the game when you lose, win or otherwise ends the game.
     */
    public abstract void end();

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

    /**
     * Returns the GameData of this game.
     *
     * @return the GameData of the game
     */
    public GameData getGameData() {
        return gameData;
    }

    /**
     * Sets the GameData of this game.
     *
     * @param gameData the GameData to set
     */
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    /**
     * Sets the GameBoard of this game.
     *
     * @param board the GameBoard to set
     */
    public void setBoard(GameBoard board) {
        this.board = board;
    }

    /**
     * Sets the GameStrategy (controller) of this game.
     *
     * @param controller the GameStrategy to set
     */
    public void setController(GameStrategy controller) {
        this.controller = controller;
    }

    /**
     * Sets the desired controller (GameStrategy) for the game, and adds it as a key listener to the game frame.
     * All existing key listeners of the game frame are removed before the new controller is added.
     *
     * @param controller the new controller (GameStrategy) for the game
     */
    public void setDesiredController(GameStrategy controller) {
        // Remove all listeners if there are any
        for (KeyListener keyListener : getGameFrame().getKeyListeners())
            getGameFrame().removeKeyListener(keyListener);

        setController(controller);
        if (controller instanceof KeyListener)
            getGameFrame().addKeyListener((KeyListener) getController());
    }
}
