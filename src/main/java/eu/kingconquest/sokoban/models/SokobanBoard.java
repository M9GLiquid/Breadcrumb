package eu.kingconquest.sokoban.models;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entities.EntityIcon;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.sokoban.core.Sokoban;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.entities.SokobanEntityIcon;

/**
 * Class representing a Sokoban game board.
 */
public class SokobanBoard extends GameBoard {
    private final Sokoban game;

    public EntityIcon lastEntityIconMoved = null;

    /**
     * Creates a new SokobanBoard.
     *
     * @param game    The Sokoban game instance.
     * @param numRows The number of rows in the board.
     * @param numCols The number of columns in the board.
     */
    public SokobanBoard(Sokoban game, Integer numRows, Integer numCols) {
        super(numRows, numCols);
        this.game = game;
    }

    /**
     * Checks if a move is valid based on the given location.
     *
     * @param location The location to check.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean isMoveInvalid(Location location) {
        return !(grid[location.getY()][location.getX()].isWalkable());
    }

    /**
     * Checks if there is a Crate at the given location.
     *
     * @param location The location to check for a crate.
     * @return true if there is a crate at the location, false otherwise.
     */
    protected boolean isCrateAtLocation(Location location) {
        return game.getCrates().stream().anyMatch(crate -> crate.getLocation().equals(location));
    }

    /**
     * Executes a move in the game, updating the player and crate positions if possible.
     *
     * @param direction The location change (movement direction).
     */
    @Override
    public void makeMove(Location direction) {

        Player player = game.getPlayer();
        Location newPlayerLocation = player.getLocation().add(direction);
        if (isMoveInvalid(newPlayerLocation)) return;

        Crate crate = findCrateAtLocation(newPlayerLocation);

        // Crate in location in
        if (crate != null) {
            Location newCrateLocation = crate.getLocation().add(direction);
            if (isCrateAtLocation(newCrateLocation) || isMoveInvalid(newCrateLocation)) return;

            crate.setLocation(newCrateLocation);

            // Check if crate is in position
            updateCrateEntityType(crate);

            lastEntityIconMoved = crate.getEntityType();
            game.getController().notifyAudioObservers();
        }
        lastEntityIconMoved = player.getEntityType();
        game.getController().notifyAudioObservers();
        player.setLocation(newPlayerLocation);

        // Level Win Condition
        if (isLevelComplete())
            setState(GameState.LEVEL_COMPLETE);

        // Game Over Condition
        if (isGameOver())
            setState(GameState.GAME_OVER);
    }

    /**
     * Updates the EntityType of the crate based on its position on the board.
     *
     * @param crate The Crate to update.
     */
    protected void updateCrateEntityType(Crate crate) {
        EntityIcon entityIcon = grid[crate.getLocation().getY()][crate.getLocation().getX()].getEntityType();

        if (entityIcon.equals(SokobanEntityIcon.GROUND_MARKED)) {
            crate.setEntityType(SokobanEntityIcon.CRATE_MARKED);
            return;
        }

        if (crate.getEntityType().equals(SokobanEntityIcon.CRATE_MARKED))
            crate.setEntityType(SokobanEntityIcon.CRATE);
    }

    /**
     * Checks if the game is over, meaning the player cannot move any of the crates to their designated storage locations.
     *
     * @return true if the game is over, false otherwise.
     */
    protected boolean isGameOver() {
        for (Crate crate : game.getCrates()) {
            // Continue if crate is on its spot
            EntityIcon entityIcon = grid[crate.getLocation().getY()][crate.getLocation().getX()].getEntityType();
            if (entityIcon.equals(SokobanEntityIcon.GROUND_MARKED))
                continue;
            // Check if crate is blocked
            Location crateLocation = crate.getLocation();
            boolean up = (isMoveInvalid(crateLocation.add(new Location(-1, 0))));
            boolean down = (isMoveInvalid(crateLocation.add(new Location(1, 0))));
            boolean left = (isMoveInvalid(crateLocation.add(new Location(0, 1))));
            boolean right = (isMoveInvalid(crateLocation.add(new Location(0, -1))));

            if ((up || down) && (right || left))
                return true;

        }
        return false;
    }


    /**
     * Finds a crate at the given location, if present.
     *
     * @param location The location to check for a crate.
     * @return The Crate at the location, or null if no crate is present.
     */
    protected Crate findCrateAtLocation(Location location) {
        return game.getCrates().stream()
                .filter(crate -> crate.getLocation().equals(location))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if the level is complete, meaning all marked position has a crate on them.
     *
     * @return true if the level is complete, false otherwise.
     */
    protected boolean isLevelComplete() {
        long count = game.getGoals().stream()
                .filter(goal -> game.getCrates().stream()
                        .anyMatch(crate -> crate.getLocation().equals(goal.getLocation())))
                .count();

        return count == game.getGoals().size();
    }
}
