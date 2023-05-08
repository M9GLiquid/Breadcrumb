package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.controllers.GameController;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.observers.StateObserver;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.PauseMenu;
import eu.kingconquest.framework.views.GameView;
import eu.kingconquest.framework.ui.Notification;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.sokoban.audio.SokobanAudioObserver;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.io.LevelReader;
import eu.kingconquest.sokoban.ui.GameOverScreen;

import java.awt.event.KeyListener;
import java.util.List;
import java.util.stream.Collectors;

public class Sokoban extends Game {
    public int level;

    public Sokoban(GameFrame gameFrame) {
        super(gameFrame,"Sokoban");

        // Game Board setup
        board = new SokobanBoard(this, null, null);
        board.setState(GameState.INITIATING);
        // Game Controller setup
        controller = new GameController(board);

        // Key Listener setup
        // Remove all listeners if there already is a key listener
        for (KeyListener keyListener : getGameFrame().getKeyListeners())
            getGameFrame().removeKeyListener(keyListener);

        getGameFrame().addKeyListener(getController());

        // Game View setup
        getGameFrame().addView(new StartMenu(this), 970, 640);

        // Game Audio setup
        controller.addAudioObserver(new SokobanAudioObserver());

        // Game Observers Setup
        controller.addObserver(new StateObserver(this));
        controller.notifyObservers();
    }

    @Override
    public void initiate() {
        level = -1;
    }

    @Override
    public void start() {
        getBoard().setState(GameState.INITIATING);
        getController().notifyObservers();
        GameView gameView = new GameView(getBoard());
        getGameFrame().addView(gameView,
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
        getGameFrame().setView(gameView);


        nextLevel();
        getBoard().setState(GameState.RUNNING);
    }

    public void nextLevel() {
        if (getBoard().getState().equals(GameState.LEVEL_COMPLETE) || getBoard().getState().equals(GameState.INITIATING)) {
            getBoard().entities.clear();
            LevelReader.loadLevel("levels.txt", this, ++level);
        }
        getGameFrame().setView(new GameView(getBoard()));
        getGameFrame().addView(getGameFrame().getView(),
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
        gameFrame.revalidate();
        gameFrame.repaint();
        gameFrame.requestFocusInWindow();
    }


    @Override
    public void reset() {
        getBoard().setState(GameState.RESETTING);
        level--;
        nextLevel();
    }

    @Override
    public void save() {
        setGameData(new GameData(getBoard().grid, getBoard().getEntities(), level));
        String message = DataWriter.save(this);

        // Show notification
        Notification.showNotification(this, message);
    }

    @Override
    public void load() {
        String message;
        boolean loaded = DataReader.load(this);
        if (loaded) {
            setData();
            message = "Game has been loaded successfully!";
        }else
            message = "GameData not loaded!";

        Notification.showNotification(this, message); // Show notification
        getBoard().setState(GameState.RUNNING);
        start();
    }

    private void setData() {
        level = getGameData().level;
        getBoard().grid = getGameData().grid;
        getBoard().entities = getGameData().entities;
        getBoard().COLS = getBoard().grid[0].length;
        getBoard().ROWS = getBoard().grid.length;
    }

    public List<Crate> getCrates() {
        return getBoard().entities.stream()
                .filter(entity -> entity instanceof Crate)
                .map(entity -> (Crate) entity)
                .collect(Collectors.toList());
    }

    public Player getPlayer() {
        return (Player) getBoard().entities.stream()
                .filter(entity -> entity instanceof Player)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void pause() {
        getGameFrame().addView(new PauseMenu(this), 970, 640);
    }

    public void gameOver() {
        getGameFrame().addView(new GameOverScreen(this), 970, 640);
    }
}
