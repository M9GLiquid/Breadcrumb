package eu.kingconquest.sokoban.io;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.utils.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * The GameData class is used to store game data such as the grid of Tiles and the list of entities.
 * This class implements the Serializable interface, which allows the game data to be saved to and loaded from files.
 * </p>
 * <p>
 * TODO: Add capability for multi save/load
 * Instance fields are used for the grid and entities instead of static fields because each instance of GameData
 * should be able to store its own unique grid and list of entities. Using instance fields allows the
 * management of multiple game data instances, such as handling multiple save files or game sessions.
 * If static fields were used, all instances of GameData would share the same grid and entities, which
 * would limit the ability to manage separate game data instances.
 * </p>
 */
public class GameData implements Serializable {

    public ArrayList<Entity> entities;
    // Data to be Saved/Loaded
    public Tile[][] grid;

    public int level;

    /**
     * Constructs a new GameData object with the specified grid and list of entities.
     *
     * @param grid The 2D array of Tiles representing the game grid.
     * @param entities The list of entities in the game.
     */
    public GameData(Tile[][] grid, ArrayList<Entity> entities, int level){
        this.grid = grid;
        this.entities = entities;
        this.level = level;
    }
}
