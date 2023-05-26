package eu.kingconquest.maze.models;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entity.EntityIcon;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.maze.core.Maze;
import eu.kingconquest.maze.entities.MazeEntityIcon;
import eu.kingconquest.maze.entities.MazePlayer;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.entities.SokobanEntityIcon;


public class MazeBoard extends GameBoard {
    private final Maze game;
    public EntityIcon lastEntityIconMoved = null;

    public MazeBoard(Maze game, Integer numRows, Integer numCols){
        super(numRows, numCols);
        this.game = game;

    }

    @Override
    protected boolean isMoveInvalid(Location location) {
        //Not able to walk through the wall
        return !(grid[location.getY()][location.getX()].isWalkable());
    }
    protected Crate findCrateAtLocation(Location location) {
        return game.getCrates().stream()
                .filter(crate -> crate.getLocation().equals(location))
                .findFirst()
                .orElse(null);
    }

    protected void updateCrateEntityType(Crate crate) {
        EntityIcon entityIcon = grid[crate.getLocation().getY()][crate.getLocation().getX()].getEntityType();

        if (entityIcon.equals(SokobanEntityIcon.GROUND_MARKED)) {
            crate.setEntityType(MazeEntityIcon._MARKED);
            return;
        }

        if (crate.getEntityType().equals(SokobanEntityIcon.CRATE_MARKED))
            crate.setEntityType(SokobanEntityIcon.CRATE);
    }

    @Override
    public void makeMove(Location direction) {

        MazePlayer player = game.getPlayer();
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



    }



    protected boolean MazeisLevelComplete() {
        long count = game.getGoals().stream()
                .filter(goal -> game.getGoals().stream()
                        .anyMatch(marker -> marker.getLocation().equals(goal.getLocation())))
                .count();

        return count == game.getGoals().size();
    }

}
