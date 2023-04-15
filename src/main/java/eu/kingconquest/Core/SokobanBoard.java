package eu.kingconquest.Core;

import eu.kingconquest.Entities.Crate;
import eu.kingconquest.Entities.Player;
import eu.kingconquest.Utils.Location;
import eu.kingconquest.api.GameBoard;

public class SokobanBoard extends GameBoard {
    private final Sokoban game;

    public SokobanBoard(Sokoban game, int numRows, int numCols) {
        super(numRows, numCols);
        this.game = game;
    }

    private boolean isWithinBorder(Location location) {
        return location.getX() >= 0 && location.getY() >= 0 && location.getX() < numRows && location.getY() < numCols;
    }

    @Override
    public boolean isMoveValid(Location location) {
        return (!isWithinBorder(location) && !grid[location.getX()][location.getY()].isWalkable());
    }

    /**
     * Check if there is a Crate at the location
     * @param location - target location to look for a crate.
     * @return true if there is a crate, otherwise false.
     */
    private boolean isCrateAtLocation(Location location) {
        return game.crates.stream().anyMatch(crate -> crate.getLocation().equals(location));
    }

    /**
     * Game Movement Logic, move the player and crate if possible.
     * @param location - location change
     */
    @Override
    public void makeMove(Location location) {
        Player player = game.player;
        Location newPlayerLocation = player.getLocation().add(location);

        if (isMoveValid(newPlayerLocation)) return;

        Crate crate = findCrateAtLocation(newPlayerLocation);

        if (crate != null) {
            Location newCrateLocation = crate.getLocation().add(location);
            if (isCrateAtLocation(newCrateLocation) || isMoveValid(newCrateLocation)) return;

            crate.setLocation(newCrateLocation);
        }

        player.setLocation(newPlayerLocation);
        game.gamePanel.repaint();
    }

    private Crate findCrateAtLocation(Location location) {
        for (Crate crate : game.crates)
            if (crate.getLocation().equals(location))
                return crate;
        return null;
    }
}
