package eu.kingconquest.framework.core;

import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.views.GameFrame;

/**
 * The Game class is an abstract base class for various game implementations.
 * Each Game contains a GameBoard, GameController, GameState, GamePanel, and a list of entities.
 * It provides methods for initiating, starting, resetting, and generating levels for the game,
 * as well as checking if the game is finished and accessing game components.
 */
public abstract class Game {
    private final String title;
    protected GameBoard board;
    protected GameState state;
    protected GameController controller;
    protected GameFrame gameFrame;

    protected GameData gameData;

    protected Game(GameFrame gameFrame, String title) {
        this.gameFrame = gameFrame;
        this.title = title;
    }

    /**
     * Initializes the game by setting up necessary objects and configurations.
     */
    public abstract void initiate();

    public abstract void gameOver();
    /**
     * Starts the game, handling game logic and progression.
     */
    public abstract void start();

    /**
     * Resets the game to its initial state.
     */
    public abstract void reset();

    public abstract void save();
    public abstract void load();

    /**
     * Checks if the game is finished.
     *
     * @return true if the game is not in the GAME_OVER state, false otherwise
     */
    public boolean isFinished() {
        return (state != GameState.GAME_OVER);
    }

    /**
     * Gets the GameBoard associated with the game.
     *
     * @return the GameBoard object
     */
    public GameBoard getBoard() {
        return board;
    }


    /**
     * Gets the GameState of the game.
     *
     * @return A GameState Object
     */
    public GameState getState(){
        return state;
    }

    /**
     * Sets the Game State of the game.
     */
    public void setState(GameState state){
        this.state = state;
    }

    /**
     * Gets the GameController of the game.
     *
     * @return A GameController Object
     */
    public GameController getController() {
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
}
