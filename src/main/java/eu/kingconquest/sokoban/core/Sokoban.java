package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameController;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.core.StateObserver;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.views.GameFrame;
import eu.kingconquest.framework.views.GameView;
import eu.kingconquest.framework.views.Notification;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.sokoban.audio.SokobanAudioObserver;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.io.LevelReader;
import eu.kingconquest.sokoban.ui.GameOverScreen;
import eu.kingconquest.sokoban.ui.StartMenu;

import java.awt.event.KeyListener;
import java.util.List;
import java.util.stream.Collectors;

public class Sokoban extends Game {
    public int level;

    public Sokoban(GameFrame gameFrame) {
        super(gameFrame,"Sokoban");
        state = GameState.INITIATING;
        // Game Controller setup
        controller = new GameController(this);

        // Game Board setup
        board = new SokobanBoard(this, null, null);

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
        nextLevel();
        setState(GameState.RUNNING);
    }

    public void nextLevel() {
        if (getState().equals(GameState.LEVEL_COMPLETE) || getState().equals(GameState.INITIATING)) {
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
        state = GameState.RESETTING;
        level--;
        nextLevel();
    }

    @Override
    public void save() {
        setGameData(new GameData(getBoard().grid, getBoard().getEntities(), level));
        String message = DataWriter.save(this);
        Notification.showNotification(this, message); // Show notification
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
        setState(GameState.RUNNING);
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

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public void gameOver() {
        getGameFrame().addView(new GameOverScreen(this), 970, 640);
    }
}
