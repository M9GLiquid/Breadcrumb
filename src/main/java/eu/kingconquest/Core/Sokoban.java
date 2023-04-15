package eu.kingconquest.Core;

import eu.kingconquest.Entities.Crate;
import eu.kingconquest.Entities.Ground;
import eu.kingconquest.Entities.Player;
import eu.kingconquest.Entities.Wall;
import eu.kingconquest.Utils.Location;
import eu.kingconquest.api.Game;
import eu.kingconquest.api.GameState;

import java.util.List;

public class Sokoban implements Game {
    private final SokobanBoard board;
    private final GameController controller;
    private GameState state;
    public GamePanel gamePanel;
    public Player player;
    public List<Crate> crates;

    public Sokoban(){
        controller = new GameController(this);

        gamePanel = new GamePanel(this);

        int rowSize = gamePanel.FRAME_WIDTH / Tile.TILE_SIZE;
        int colSize = gamePanel.FRAME_HEIGHT / Tile.TILE_SIZE;
        board = new SokobanBoard(this, rowSize, colSize);
    }
    @Override
    public void initiate() {
        state = GameState.INITIATING;
    }
    @Override
    public void start() {
        generateLevel();
        player = new Player(new Location(1,1));
        crates = List.of(new Crate(new Location(2,2), false), new Crate(new Location(3,5), true));

        state = GameState.RUNNING;
        gamePanel.addKeyListener(controller);
    }

    private void generateLevel() {
        for (int row = 0; row < board.numRows; row++)
            for (int col = 0; col < board.numCols; col++)
                board.grid[row][col] = isBorderTile(row, col)
                        ? new Wall(new Location(row, col))
                        : new Ground(new Location(row, col));
    }

    /**
     * Checks whether we are a border tile or not.
     * @param row -
     * @param col -
     * @return True if the Tile is a border
     */
    private boolean isBorderTile(int row, int col) {
        return row == 0 || col == 0 || row == board.numRows - 1 || col == board.numCols - 1;
    }


    @Override
    public void reset() {
        state = GameState.RESETTING;
    }

    @Override
    public boolean isFinished() {
        return (state != GameState.GAME_OVER);
    }

    public SokobanBoard getBoard(){
        return board;
    }

    @Override
    public void registerObserver(GameObserver observer) {
        controller.addObserver(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        controller.removeObserver(observer);
    }
}
