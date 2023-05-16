package eu.kingconquest.twozerofoureight.models;


import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityType;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.twozerofoureight.core.EntityType2048;
import eu.kingconquest.twozerofoureight.core.Game2048;
import eu.kingconquest.twozerofoureight.entities.Block;

import java.util.ArrayList;


public class Board2048 extends GameBoard {
    private final Game2048 game;

    public Board2048(Game2048 game, Integer numRows, Integer numCols) {
        super(numRows, numCols);
        this.game = game;
        //Creating two starting Tiles on a random empty cell.
        addNewTile();
        addNewTile();
    }

    @Override
    protected boolean isMoveInvalid(Location direction) {
        return true;
    }

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


    public boolean isLocationMatch(Location location1, Location location2) {
        return grid[location1.getX()][location1.getY()] == grid[location2.getX()][location2.getY()];
    }

    public boolean isMatch(Entity block1, Entity block2) {
        return block1.getEntityType().equals(block2.getEntityType());
    }
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
        addNewTile();
    }

    public boolean isFull(){
        int maxSize = 16;
        int size = getEntities().size();
        return size == maxSize;
    }

    public boolean entityExist(Entity entity) {
        if(entity == null)
            return false;
        for (Entity tempEntity : getEntities()) {
            if (tempEntity.getLocation().equals(entity.getLocation()))
                return true;
        }
        return false;
    }

    public boolean isGameOver() {
        if (!isMoveInvalid(new Location(-1, 0))
                && !isMoveInvalid(new Location(1, 0))
                && !isMoveInvalid(new Location(0, -1))
                && !isMoveInvalid(new Location(0, 1))) {
            game.getBoard().setState(GameState.GAME_OVER);
            return true;
        }return false;
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
            entity = new Block(new Location(randomCol, randomRow), EntityType2048.E2);
        } while (entityExist(entity));

        //Creating variables for the odds of being a value 4 for the two starting blocks.
        int Odds = (int) (Math.random() * 100 + 1);
        EntityType2048 EntityType = (Odds > 90) ? EntityType2048.E4 : EntityType2048.E2;
        entity.setEntityType(EntityType);
        addEntity(entity);
    }

    /**
     *Return the entity from a specific location on the matrix.
     */
    public Entity getEntity(Location location){
        if(location == null)
            return null;
        for(Entity entity : getEntities()){
            if(entity.getLocation().equals(location))
                return entity;
        }
        return null;
    }

    /**
     *The method returns the product of the value from the entity in the argument.
     */
    public EntityType productEntity(EntityType2048 entity){
        return switch (entity) {
            case E2 -> EntityType2048.E4;
            case E4 -> EntityType2048.E8;
            case E8 -> EntityType2048.E16;
            case E16 -> EntityType2048.E32;
            case E32 -> EntityType2048.E64;
            case E64 -> EntityType2048.E128;
            case E128 -> EntityType2048.E256;
            case E256 -> EntityType2048.E512;
            case E512 -> EntityType2048.E1024;
            case E1024 -> EntityType2048.E2048;
            case E2048 -> EntityType2048.E4096;
            case E4096 -> EntityType2048.E8192;
            case E8192 -> EntityType2048.E16384;
            default -> null;
        };
    }

    /**
     *Merge the cells in grid after compressing.
     *If cells have same values,and they are adjacent to each other,
     *they are merged together and their value is doubled.
     */
    private void merge(){
        for(int col = 1; col < 5; col++){
            for(int row = 1; row < 4; row++){
                // If the adjacent cells have the same values and the specific cell is not empty.
                Entity entity = getEntity(new Location(col, row));
                Entity entity2 = getEntity(new Location(col, row+1));

                if(!entityExist(entity) || !entityExist(entity2))
                    continue;

                // If the adjacent cells have the same values and the specific cell is not empty.
                if(isMatch(entity, entity2)){
                    //Get the value from a specific entity.
                    EntityType2048 entityType = (EntityType2048) entity.getEntityType();
                    //Create a new block on the same location as the specific entity with doubled value.
                    Block block = new Block(new Location(col, row), productEntity(entityType));
                    //Remove the entity on the specific colum and row.
                    getEntities().remove(entity);
                    ////Add the new entity with doubled value on the specific colum and row.
                    getEntities().add(block);
                    //Remove the entity next to the entity with the specific column and row.
                    getEntities().remove(getEntity(new Location(col, row+1)));
                }
            }
        }
    }

    /**
     * Reverse the contents of the grid.
     * All values of cells are swapped with the corresponding cell in the opposite column.
     */
    private void reverse(){
        ArrayList<Entity> entities = new ArrayList<>();
        for(int col = 1; col < 5; col++){
            for(int row = 1; row < 5; row++){
                Location location = new Location(col, 5-row);
                Block block = new Block(location, EntityType2048.WALL);

                //Get the value of the corresponding cell in the opposite column.
                if(!entityExist(block))
                    continue;
                EntityType entitytype = getEntity(location).getEntityType();
                //Create a new block on the current cell with the value from the corresponding cell in the opposite column.
                block.setEntityType(entitytype);
                //Add the new block to the temporary entity list.
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
    private void transpose(){
        ArrayList<Entity> entities = new ArrayList<>();
        for(int col = 1; col < 5; col++){
            for(int row = 1; row < 5; row++){
                //Create a new block on the current cell with the value from the transposed cell.
                Block block = new Block(new Location(col, row), EntityType2048.E2);
                if(!entityExist(block))
                    continue;
                //Get the value of the transpose cell.
                EntityType entitytype = getEntity(new Location(col, row)).getEntityType();
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
    private void compress(){
        ArrayList<Entity> entities = new ArrayList<>();
        for(int col = 1; col < 5; col++){
            int pos = 1;
            for(int row = 1; row < 5; row++){
                if(getEntity(new Location(col, row)) != null){
                    //Get the value from the specific cell.
                    EntityType entitytype = getEntity(new Location(col, row)).getEntityType();
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
