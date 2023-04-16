package eu.kingconquest.Core;

import eu.kingconquest.Entities.Crate;
import eu.kingconquest.Entities.Ground;
import eu.kingconquest.Entities.Player;
import eu.kingconquest.Entities.Wall;
import eu.kingconquest.UI.GameOver;
import eu.kingconquest.Utils.Location;
import eu.kingconquest.api.Game;
import eu.kingconquest.api.GameState;

import java.util.List;

public class Sokoban implements Game {
    private final SokobanBoard board;
    private final GameController controller;
    private GameState state;
    private final GamePanel gamePanel;
    private Player player;
    private List<Crate> crates;
    public int level;

    public Sokoban(){
        state = GameState.INITIATING;
        controller = new GameController(this);

        gamePanel = new GamePanel(this);

        int rowSize = gamePanel.FRAME_WIDTH / Tile.TILE_SIZE;
        int colSize = gamePanel.FRAME_HEIGHT / Tile.TILE_SIZE;
        board = new SokobanBoard(this, rowSize, colSize);
        controller.notifyObservers();
        gamePanel.addKeyListener(controller);
    }
    @Override
    public void initiate() {
        level = -1;
    }

    @Override
    public void start() {
        level++;
        generateLevel();
        player = new Player(new Location(1,1));
        crates = List.of(new Crate(new Location(2,2)), new Crate(new Location(3,5)));

        state = GameState.RUNNING;
    }

    private void generateLevel() {
        for (int row = 0; row < board.numRows; row++)
            for (int col = 0; col < board.numCols; col++)
                if (isBorderTile(row, col))
                    board.grid[row][col] = new Wall(new Location(row, col));
                else
                    board.grid[row][col] = new Ground(new Location(row, col));
        modifyLevel();
    }

    private void modifyLevel() {
        switch (level) {
            case 0 -> {
                board.grid[5][5].setEntityType(EntityType.GROUND_MARKED);
                board.grid[7][4].setEntityType(EntityType.GROUND_MARKED);
                board.grid[6][12].setEntityType(EntityType.GROUND_MARKED);
            }
            case 1 -> {
                board.grid[5][12].setEntityType(EntityType.GROUND_MARKED);
                board.grid[12][12].setEntityType(EntityType.GROUND_MARKED);
                board.grid[8][9].setEntityType(EntityType.GROUND_MARKED);
            }
        }
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

    public List<Crate> getCrates() {
        return crates;
    }

    public Player getPlayer() {
        return player;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setState(GameState state){
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public void gameOver() {
        new GameOver(this);
    }
}
