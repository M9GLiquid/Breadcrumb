package eu.kingconquest.framework;

import eu.kingconquest.framework.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game class is an abstract base class for various game implementations.
 * Each Game contains a GameBoard, GameController, GameState, GamePanel, and a list of entities.
 * It provides methods for initiating, starting, resetting, and generating levels for the game,
 * as well as checking if the game is finished and accessing game components.
 */
public abstract class Game {
    protected GameBoard board;
    protected GameState state;
    protected GameController controller;
    protected GamePanel gamePanel;

    protected List<Entity> entities = new ArrayList<>();

    /**
     * Initializes the game by setting up necessary objects and configurations.
     */
    protected abstract void initiate();

    /**
     * Starts the game, handling game logic and progression.
     */
    public abstract void start();

    /**
     * Resets the game to its initial state.
     */
    protected abstract void reset();

    /**
     * Generates a new level for the game.
     */
    protected abstract void generateLevel();

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
    public List<Entity> getEntities(){
        return entities;
    }

    public GameState getState(){
        return state;
    }
    public void setState(GameState state){
        this.state = state;
    }

    public GameController getController() {
        return controller;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }


    public int getFrameWidth() {
        return (GameBoard.ROWS * Tile.TILE_SIZE) + 16;
    }

    public int getFrameHeight() {
        return (GameBoard.COLS * Tile.TILE_SIZE) + 39;
    }
}
