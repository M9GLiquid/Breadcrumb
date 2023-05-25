package eu.kingconquest.twozerofoureight.models;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityIcon;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.twozerofoureight.core.EntityIcon2048;
import eu.kingconquest.twozerofoureight.core.Game2048;
import eu.kingconquest.twozerofoureight.entities.Block;

import java.util.ArrayList;

/**
 * The Board2048 class represents the game board for the 2048 game.
 * It extends the GameBoard class and implements the logic for game moves,
 * merging cells, checking game over conditions, and adding new tiles.
 */
public class Board2048 extends GameBoard {
    protected final Game2048 game;
    protected int score;

    /**
     * Constructs a Board2048 object with the specified game, number of rows, and number of columns.
     * @param game The Game2048 instance associated with the board.
     * @param numRows The number of rows on the board.
     * @param numCols The number of columns on the board.
     */
    public Board2048(Game2048 game, Integer numRows, Integer numCols) {
        super(numRows, numCols);
        this.game = game;
        //Creating two starting Tiles on a random empty cell.
        addNewTile();
        addNewTile();
    }

    /**
     * Returns the current score of the game.
     * @return the value of the score.
     */
    public int getScore(){
        return score;
    }

    /**
     * Resets the score.
     */
    public void resetScore(){
        score = 0;
    }

    @Override
    protected boolean isMoveInvalid(Location direction) {
        return true;
    }

    /**
     * Checks if a move in the specified direction is valid for the given block.
     * @param block The block to be moved.
     * @param direction The direction in which the move is to be made.
     * @return true if the move is valid, false otherwise.
     */
    protected boolean isMoveValid(Entity block, Location direction) {
        Location location = block.getLocation();

        for (Entity entity : getEntities())
        {
            // To the right
            if (direction.getX() == 1)
            {
                //If a block furthest to the right is inserted
                if (location.getX() == 4)
                    return false;

                //Set location for the next Entity in x-axis.
                location.setX(location.getX() + 1);

                //Check if the current Entity is the same as the next Entity in x-axis.
                if (isLocationMatch(entity.getLocation(), location)) {
                    if (block.getEntityType() == entity.getEntityType())
                        return true;
                }
            } // To the left
            else if (direction.getX() == -1)
            {
                //If a block furthest to the left is inserted
                if (block.getLocation().getX() == 1)
                    return false;

                //Set location for the previous Entity in x-axis.
                location.setX(location.getX() - 1);
                //Check if the current Entity is the same as the previous Entity in x-axis.
                if (isLocationMatch(entity.getLocation(), location))
                    if (block.getEntityType() == entity.getEntityType())
                        return true;

            }
            else if (direction.getY() == 1)
            {
                //If a block from the bottom is inserted
                if (block.getLocation().getY() == 4)
                    return false;

                //Set location for the next Entity in negative y-axis.
                location.setY(location.getY() + 1);

                //Check if the current Entity is the same as the next Entity in positive y-axis.
                if (isLocationMatch(entity.getLocation(), location))
                    if (block.getEntityType() == entity.getEntityType())
                        return true;
            }
            else if (direction.getY() == -1)
            {
                //If a block from the bottom is inserted
                if (block.getLocation().getY() == 1)
                    return false;

                //Set location for the next Entity in negative y-axis.
                location.setY(location.getY() - 1);

                //Check if the current Entity is the same as the next Entity in negative Y-axis.
                if (isLocationMatch(entity.getLocation(), location))
                    if (block.getEntityType() == entity.getEntityType())
                        return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given locations match in the grid.
     * @param location1 The first location.
     * @param location2 The first second.
     * @return true if the locations match, false otherwise.
     */
    public boolean isLocationMatch(Location location1, Location location2) {
        return grid[location1.getX()][location1.getY()] == grid[location2.getX()][location2.getY()];
    }

    /**
     * Checks if the given blocks match.
     * @param block1 The first block.
     * @param block2 The second block.
     * @return true if the blocks match, false otherwise.
     */
    public boolean isMatch(Entity block1, Entity block2) {
        return block1.getEntityType().equals(block2.getEntityType());
    }

    /**
     * Makes a move in the specified direction on the game board.
     * @param direction the direction in which the move should be made.
     */
    @Override
    public void makeMove(Location direction) {
        if(direction.getX() == 1){
            transpose();
            reverse();
            compress();
            merge();
            compress();
            reverse();
            transpose();
        }
        else if(direction.getX() == -1){
            transpose();
            compress();
            merge();
            compress();
            transpose();
        }
        else if(direction.getY() == 1){
            reverse();
            compress();
            merge();
            compress();
            reverse();
        }
        else if(direction.getY() == -1){
            compress();
            merge();
            compress();
        }
        if(isGameOver())
            game.getBoard().setState(GameState.GAME_OVER);
        game.getController().notifyAudioObservers();
        addNewTile();
    }

    /**
     * Checks if the game board is full.
     * @return true if the game board is full, false otherwise.
     */
    public boolean isFull(){
        int maxSize = 16;
        int size = getEntities().size();
        return size == maxSize;
    }

    /**
     *Checks if any entity exists on the board.
     * @param entity The entity we want to check if exists.
     * @return true if the entity exists.
     */
    public boolean entityExist(Entity entity) {
        if(entity == null)
            return false;
        for (Entity tempEntity : getEntities()) {
            if (tempEntity.getLocation().equals(entity.getLocation()))
                return true;
        }
        return false;
    }

    /**
     * Check to see if any move is possible on the board.
     * @return true if adjacent matching tiles horizontally or vertically.
     */
    public boolean isAnyMovePossible() {
        // Check if there are any adjacent matching tiles horizontally
        for (int col = 1; col < 4; col++) {
            for (int row = 1; row <= 4; row++) {
                Entity entity = getEntity(new Location(col, row));
                Entity rightEntity = getEntity(new Location(col + 1, row));

                if (entityExist(entity) && entityExist(rightEntity) && isMatch(entity, rightEntity))
                    return true;
            }
        }

        // Check if there are any adjacent matching tiles vertically
        for (int row = 1; row < 4; row++) {
            for (int col = 1; col <= 4; col++) {
                Entity entity = getEntity(new Location(col, row));
                Entity bottomEntity = getEntity(new Location(col, row + 1));

                if (entityExist(entity) && entityExist(bottomEntity) && isMatch(entity, bottomEntity))
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        // Check if the board is full and no valid moves are possible
        return isFull() && !isAnyMovePossible();
    }

    /**
     * Checks if the game has won.
     */
    public void isWin() {
        for (Entity entity : getEntities()) {
            //Checks if there is a tile with the value 64.
            if (entity.getEntityType() == EntityIcon2048.E64) {
                game.hasWon = true;
                game.getBoard().setState(GameState.WIN);
            }
        }
    }

    /**
     * Add a new 'tile' of value 2 or 4 in grid at any random empty cell.
     */
    public void addNewTile(){
        if(isFull())
            return;
        int randomCol, randomRow;
        Block entity;
        //Find a random column and row on an empty cell.
        do {
            randomCol = (int) (Math.random() * 4 + 1);
            randomRow = (int) (Math.random() * 4 + 1);
            entity = new Block(new Location(randomCol, randomRow), EntityIcon2048.E2);
        } while (entityExist(entity));

        //Creating variables for the odds of being a value 4 for the two starting blocks.
        int Odds = (int) (Math.random() * 100 + 1);
        EntityIcon2048 EntityType = (Odds > 90) ? EntityIcon2048.E4 : EntityIcon2048.E2;
        entity.setEntityType(EntityType);
        addEntity(entity);
    }

    /**
     * The method returns the product of the value from the entity in the argument.
     * @param entity The value the method square up to.
     * @return the product of the value.
     */
    public EntityIcon productEntity(EntityIcon2048 entity){
        return switch (entity) {
            case E2 -> EntityIcon2048.E4;
            case E4 -> EntityIcon2048.E8;
            case E8 -> EntityIcon2048.E16;
            case E16 -> EntityIcon2048.E32;
            case E32 -> EntityIcon2048.E64;
            case E64 -> EntityIcon2048.E128;
            case E128 -> EntityIcon2048.E256;
            case E256 -> EntityIcon2048.E512;
            case E512 -> EntityIcon2048.E1024;
            case E1024 -> EntityIcon2048.E2048;
            case E2048 -> EntityIcon2048.E4096;
            case E4096 -> EntityIcon2048.E8192;
            case E8192 -> EntityIcon2048.E16384;
            default -> null;
        };
    }

    /**
     * The method adds the sum to the score.
     * @param entity The value the method sum up.
     */
    public void addScore(EntityIcon2048 entity){
        switch (entity) {
            case E2 -> score += 4;
            case E4 -> score += 8;
            case E8 -> score += 16;
            case E16 -> score += 32;
            case E32 -> score += 64;
            case E64 -> score += 128;
            case E128 -> score += 256;
            case E256 -> score += 512;
            case E512 -> score += 1024;
            case E1024 -> score += 2048;
            case E2048 -> score += 4096;
            case E4096 -> score += 8192;
            case E8192 -> score += 16384;
            default -> {
            }
        }
    }

    /**
     *Merge the cells in grid after compressing.
     *If cells have same values,and they are adjacent to each other,
     *they are merged together and their value is doubled.
     */
    protected void merge(){
        for(int col = 1; col < 5; col++){
            for(int row = 1; row < 4; row++){
                Entity entity = getEntity(new Location(col, row));
                Entity entity2 = getEntity(new Location(col, row+1));

                if(!entityExist(entity) || !entityExist(entity2))
                    continue;

                // If the adjacent cells have the same values.
                if(isMatch(entity, entity2)){
                    //Get the value from a specific entity.
                    EntityIcon2048 entityType = (EntityIcon2048) entity.getEntityType();
                    //Create a new block on the same location as the specific entity with doubled value.
                    Block block = new Block(new Location(col, row), productEntity(entityType));
                    //Remove the entity from the specific colum and row.
                    getEntities().remove(entity);
                    ////Add the new entity with doubled value on the specific colum and row.
                    getEntities().add(block);
                    //Remove the entity next to the entity with the specific column and row.
                    getEntities().remove(getEntity(new Location(col, row+1)));
                    addScore((EntityIcon2048) entity.getEntityType());
                }
            }
        }
        if (!game.hasWon)
            isWin();
    }

    /**
     * Reverse the contents of the grid.
     * All values of cells are swapped with the corresponding cell in the opposite column.
     */
    protected void reverse(){
        ArrayList<Entity> entities = new ArrayList<>();
        for(int col = 1; col < 5; col++){
            for(int row = 1; row < 5; row++){
                Location location = new Location(col, 5-row);
                Block block = new Block(location, EntityIcon2048.WALL);

                //Get the value of the corresponding cell in the opposite column.
                if(!entityExist(block))
                    continue;
                EntityIcon entitytype = getEntity(location).getEntityType();
                //Create a new block on the current cell with the value from the corresponding cell in the opposite column.
                block.setEntityType(entitytype);
                //Add the new block to the temporary entity list and set the correct location.
                block.setLocation(new Location(col, row));
                entities.add(block);
            }
        }
        setEntities(entities);
    }

    /**
     * Get the transpose of grid.
     * All values of cells are swapped with the corresponding cell in the opposite row.
     */
    protected void transpose(){
        ArrayList<Entity> entities = new ArrayList<>();
        for(int col = 1; col < 5; col++){
            for(int row = 1; row < 5; row++){
                //Create a new block on the current cell with the value from the transposed cell.
                Block block = new Block(new Location(col, row), EntityIcon2048.E2);
                if(!entityExist(block))
                    continue;
                //Get the value of the transpose cell.
                EntityIcon entitytype = getEntity(new Location(col, row)).getEntityType();
                block.setEntityType(entitytype);
                block.setLocation(new Location(row,col));
                //Add the new block to the temporary entity list.
                entities.add(block);
            }
        }
        setEntities(entities);
    }

    /**
     * Compress the grid after every step before and after merging cells.
     * Empty cells from the grid are removed in this step.
     */
    protected void compress(){
        ArrayList<Entity> entities = new ArrayList<>();
        for(int col = 1; col < 5; col++){
            int pos = 1;
            for(int row = 1; row < 5; row++){
                if(getEntity(new Location(col, row)) != null){
                    //Get the value from the specific cell.
                    EntityIcon entitytype = getEntity(new Location(col, row)).getEntityType();
                    //Create a new block on an empty spot to compress.
                    Block block = new Block(new Location(col, pos), entitytype);
                    entities.add(block);
                    pos++;
                }
            }
        }
        setEntities(entities);
    }
}
