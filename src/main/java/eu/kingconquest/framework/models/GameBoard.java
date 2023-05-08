package eu.kingconquest.framework.models;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.utils.Tile;

import java.util.ArrayList;

/**
 * The GameBoard class is an abstract base class for various game board implementations.
 * Each GameBoard contains a grid of Tiles, the number of rows, and the number of columns.
 * It provides methods for creating a GameBoard, checking if a move is valid, and making a move.
 */
public abstract class GameBoard {
    public int COLS = 30;
    public int ROWS = 20;

    private ArrayList<Entity> entities = new ArrayList<>();
    public Tile[][] grid;

    protected GameState state;

    /**
     * Creates a GameBoard with the specified number of rows and columns.
     */
    public GameBoard(Integer numRows, Integer numCols) {
        if (numRows != null)
            ROWS = numRows;
        if (numCols != null)
            COLS = numCols;

        grid = new Tile[COLS][ROWS];
    }
    /**
     * Add entity to list of entities
     */
    public void addEntity(Entity entity){
        entities.add(entity);
    }

    /**
     * Gets the Entities associated with the game.
     *
     * @return List of Entities
     */
    public ArrayList<Entity> getEntities(){
        return entities;
    }
    public void setEntities(ArrayList<Entity> entities){
        this.entities = entities;
    }

    /**
     * Checks if a move to the specified location is invalid.
     *
     * @param location the location to be checked
     * @return true if the move is valid, false otherwise
     */
    protected abstract boolean isMoveInvalid(Location location);


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
     * Makes a move on the game board in the specified direction.
     * The implementation should include game-specific move logic.
     *
     * @param direction the direction in which the move should be made
     */
    public abstract void makeMove(Location direction);

}




