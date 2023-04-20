package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.*;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.sokoban.audio.SokobanAudioObserver;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Ground;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.entities.Wall;
import eu.kingconquest.sokoban.ui.GameOverScreen;
import eu.kingconquest.sokoban.ui.StartMenu;

import java.util.List;
import java.util.stream.Collectors;

public class Sokoban extends Game {
    public int level;


    public Sokoban() {
        state = GameState.INITIATING;
        controller = new GameController(this);

        board = new SokobanBoard(this, null, null);

        gamePanel = new GamePanel(this);
        controller.addObserver(new StateObserver(this));
        controller.addAudioObserver(new SokobanAudioObserver());
        gamePanel.addMenu(new StartMenu(this, gamePanel));
        controller.notifyObservers();
        gamePanel.addKeyListener(controller);
    }

    @Override
    public void initiate() {
        entities.add(new Player(new Location(1, 1)));
        level = 0;
    }

    @Override
    public void start() {
        generateLevel();
        nextLevel();
    }

    @Override
    protected void generateLevel() {
        for (int row = 0; row < GameBoard.ROWS; row++)
            for (int col = 0; col < GameBoard.COLS; col++)
                if (isBorderTile(row, col))
                    board.grid[row][col] = new Wall(new Location(row, col));
                else
                    board.grid[row][col] = new Ground(new Location(row, col));
    }

    public void nextLevel() {
        entities.removeAll(getCrates());
        switch (level++) {
            case 0 -> {
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][5].setEntityType(SokobanEntityType.GROUND_MARKED);
                board.grid[7][4].setEntityType(SokobanEntityType.GROUND_MARKED);
                board.grid[6][12].setEntityType(SokobanEntityType.GROUND_MARKED);
                entities.addAll(List.of(new Crate(new Location(2, 2)), new Crate(new Location(3, 5)), new Crate(new Location(10, 5))));
            }
            case 1 -> {
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][12].setEntityType(SokobanEntityType.GROUND_MARKED);
                board.grid[12][12].setEntityType(SokobanEntityType.GROUND_MARKED);
                board.grid[8][9].setEntityType(SokobanEntityType.GROUND_MARKED);
                entities.addAll(List.of(new Crate(new Location(5, 2)), new Crate(new Location(5, 5))));
            }
            case 2 -> {
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.WALL);
                board.grid[5][18].setEntityType(SokobanEntityType.GROUND_MARKED);
                board.grid[12][12].setEntityType(SokobanEntityType.GROUND_MARKED);
                board.grid[8][10].setEntityType(SokobanEntityType.GROUND_MARKED);
                entities.addAll(List.of(new Crate(new Location(5, 2)), new Crate(new Location(5, 5))));
            }
            default -> // THE END
                    entities.addAll(List.of(
                            new Crate(new Location(5, 2)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5)),
                            new Crate(new Location(5, 5))
                    ));
        }
        state = GameState.RUNNING;
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    /**
     * Checks whether we are a border tile or not.
     *
     * @param row -
     * @param col -
     * @return True if the Tile is a border
     */
    private boolean isBorderTile(int row, int col) {
        return row == 0 || col == 0 || row == GameBoard.ROWS - 1 || col == GameBoard.COLS - 1;
    }


    @Override
    public void reset() {
        state = GameState.RESETTING;
    }

    public List<Crate> getCrates() {
        return entities.stream()
                .filter(entity -> entity instanceof Crate)
                .map(entity -> (Crate) entity)
                .collect(Collectors.toList());
    }

    public Player getPlayer() {
        return (Player) entities.stream()
                .filter(entity -> entity instanceof Player)
                .findFirst()
                .orElse(null);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public void gameOver() {
        new GameOverScreen(this);
    }
}
