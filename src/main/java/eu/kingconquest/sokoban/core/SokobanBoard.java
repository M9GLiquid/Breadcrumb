package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.entity.EntityType;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.core.GameBoard;
import eu.kingconquest.framework.core.GameState;

/**
 * Class representing a Sokoban game board.
 */
public class SokobanBoard extends GameBoard {
    private final Sokoban game;

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
    private boolean isCrateAtLocation(Location location) {
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
        SokobanBoard board = (SokobanBoard) game.getBoard();
        Location newPlayerLocation = player.getLocation().add(direction);
        if (isMoveInvalid(newPlayerLocation)) return;

        Crate crate = findCrateAtLocation(newPlayerLocation);

        if (crate != null) {
            Location newCrateLocation = crate.getLocation().add(direction);
            if (isCrateAtLocation(newCrateLocation) || isMoveInvalid(newCrateLocation)) return;

            crate.setLocation(newCrateLocation);

            // Check if crate is in position
            updateCrateEntityType(board, crate);

            game.getController().notifyAudioObservers(crate);
        }
        game.getController().notifyAudioObservers(game.getPlayer());
        player.setLocation(newPlayerLocation);

        // Level Win Condition
        if (isLevelComplete())
            game.setState(GameState.LEVEL_COMPLETE);

        game.getGameFrame().getView().repaint();
    }

    /**
     * Updates the EntityType of the crate based on its position on the board.
     *
     * @param board The SokobanBoard instance.
     * @param crate The Crate to update.
     */
    private void updateCrateEntityType(SokobanBoard board, Crate crate) {
        EntityType entityType = board.grid[crate.getLocation().getY()][crate.getLocation().getX()].getEntityType();

        if (entityType.equals(SokobanEntityType.GROUND_MARKED)) {
            crate.setEntityType(SokobanEntityType.CRATE_MARKED);
            return;
        }

        if (crate.getEntityType().equals(SokobanEntityType.CRATE_MARKED))
            crate.setEntityType(SokobanEntityType.CRATE);

    }

    /**
     * Finds a crate at the given location, if present.
     *
     * @param location The location to check for a crate.
     * @return The Crate at the location, or null if no crate is present.
     */
    private Crate findCrateAtLocation(Location location) {
        return game.getCrates().stream()
                .filter(crate -> crate.getLocation().equals(location))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if the level is complete, meaning all crates are on marked positions.
     *
     * @return true if the level is complete, false otherwise.
     */
    private boolean isLevelComplete() {
        return game.getCrates().stream()
                .allMatch(crate -> crate.getEntityType().equals(SokobanEntityType.CRATE_MARKED));
    }
}
