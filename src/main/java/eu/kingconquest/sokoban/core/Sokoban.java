package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.controllers.GuiController;
import eu.kingconquest.framework.controllers.KeyController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.core.StateManager;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.ui.*;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.ConsoleView;
import eu.kingconquest.framework.views.GraphicalView;
import eu.kingconquest.sokoban.audio.SokobanGameAudioObserver;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.entities.SokobanEntityIcon;
import eu.kingconquest.sokoban.io.LevelReader;
import eu.kingconquest.sokoban.models.SokobanBoard;
import eu.kingconquest.sokoban.ui.GameOverScreen;
import eu.kingconquest.sokoban.ui.WinScreen;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Sokoban extends Game {
    public boolean guiController = false;
    public int level;

    public Sokoban(GameFrame gameFrame) {
        super(gameFrame,"Sokoban");
    }

    @Override
    public void initiate() {
        // Game Board setup
        setBoard(new SokobanBoard(this, null, null));

        // Set the desired controller
        setDesiredController(new KeyController(getBoard())); // Change this line to switch between controllers

        GameFrame frame = getGameFrame();
        frame.setGameView(new GraphicalView(getBoard()));

        // Game View setup
        frame.addView(new StartMenu(this), Menu.WIDTH, Menu.HEIGHT);

        // State Observers
        getController().addStateObserver(new StateManager(this));
        // Audio Observers
        getController().addAudioObserver(new SokobanGameAudioObserver(this));
        // View Observers
        getController().addViewObserver(frame.getGameView());
        getController().addViewObserver(new ConsoleView(getBoard()));

        getBoard().setState(GameState.INITIATING);

        Tile.setTileSize(64);
        level = -1;
    }

    @Override
    public void start() {
        if (nextLevel())
            return;

        getBoard().setState(GameState.RUNNING);

        // Activate GUIControls if that is selected
        if (getController() instanceof GuiController && !guiController) {
            new FloatingBtnsView(getGameFrame(), (ActionListener) getController());
            guiController = true;
        }
    }

    protected boolean nextLevel() {
        GameState gameState = getBoard().getState();

        if (gameState.equals(GameState.LEVEL_COMPLETE) ||
                gameState.equals(GameState.INITIATING) ||
                gameState.equals(GameState.RESETTING)) {

            getBoard().getEntities().clear();
            // Get next level, int: 0 error, 1 game won, 2 next level
            int loaded = LevelReader.loadLevel("levels.txt", getBoard(), ++level);

            // We have no more levels
            if (loaded == 1) {
                getBoard().setState(GameState.WIN);
                end();
                return true;
            }
        }

        setGameView();
        return false;
    }

    protected void setGameView(){
        getGameFrame().addView(getGameFrame().getGameView(),
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
    }


    @Override
    public void restart() {
        getBoard().setState(GameState.RESETTING);
        level--;
        start();
    }

    @Override
    public void save() {
        // Upload game data
        setGameData(new GameData(getBoard().grid, getBoard().getEntities(), level));

        // Try save
        String message = DataWriter.save(this);

        // Show notification
        Notification.showNotification(this, message);
    }

    @Override
    public void load() {
        String message = DataReader.load(this);

        if (message.equals("Game loading!")) {
            setData();
            Timer timer = new Timer(1500, e -> {
                getBoard().setState(GameState.RUNNING);
                setGameView();
            });
            timer.setRepeats(false);
            timer.start();
        }
        Notification.showNotification(this, message); // Show notification
    }

    protected void setData() {
        level = getGameData().level;
        getBoard().grid = getGameData().grid;
        getBoard().setEntities(getGameData().entities);
        getBoard().COLS = getBoard().grid[0].length;
        getBoard().ROWS = getBoard().grid.length;
    }

    public List<Crate> getCrates() {
        return getBoard().getEntities().stream()
                .filter(entity -> entity instanceof Crate)
                .map(entity -> (Crate) entity)
                .collect(Collectors.toList());
    }

    public Player getPlayer() {
        return (Player) getBoard().getEntities().stream()
                .filter(entity -> entity instanceof Player)
                .findFirst()
                .orElse(null);
    }

    public List<Tile> getGoals() {
        return getBoard().getEntities().stream()
                .filter(entity -> entity.getEntityType().equals(SokobanEntityIcon.GROUND_MARKED))
                .map(entity -> (Tile) entity)
                .collect(Collectors.toList());
    }

    @Override
    public void pause() {
        getGameFrame().addView(new PauseMenu(this), Menu.WIDTH, Menu.HEIGHT);
    }

    @Override
    public void end() {
        if (getBoard().getState().equals(GameState.GAME_OVER))
            getGameFrame().addView(new GameOverScreen(this), Menu.WIDTH, Menu.HEIGHT);
        else
            getGameFrame().addView(new WinScreen(this), Menu.WIDTH, Menu.HEIGHT);
    }
}