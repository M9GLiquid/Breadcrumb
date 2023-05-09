package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.controllers.GuiController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.observers.ConsoleViewObserver;
import eu.kingconquest.framework.observers.StateObserver;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.Notification;
import eu.kingconquest.framework.ui.PauseMenu;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.GameGuiView;
import eu.kingconquest.sokoban.audio.SokobanAudioObserver;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.io.LevelReader;
import eu.kingconquest.sokoban.models.SokobanBoard;
import eu.kingconquest.sokoban.ui.GameOverScreen;

import java.util.List;
import java.util.stream.Collectors;

public class Sokoban extends Game {
    public int level;

    public Sokoban(GameFrame gameFrame) {
        super(gameFrame,"Sokoban");

        // Game Board setup
        setBoard(new SokobanBoard(this, null, null));

        // Set the desired controller
        setDesiredController(new GuiController(getBoard())); // Change this line to switch between controllers

        // Game View setup
        getGameFrame().addView(new StartMenu(this), 970, 640);

        // Game Audio setup
        getController().addAudioObserver(new SokobanAudioObserver());

        // Game Observers Setup
        getController().addStateObserver(new StateObserver(this));
        getController().addViewObserver(new ConsoleViewObserver(getBoard()));


        getBoard().setState(GameState.INITIATING);
        getController().notifyStateObservers();
    }



    @Override
    public void initiate() {
        level = -1;
    }

    @Override
    public void start() {
        nextLevel();
        getBoard().setState(GameState.RUNNING);
    }

    public void nextLevel() {
        if (getBoard().getState().equals(GameState.LEVEL_COMPLETE) || getBoard().getState().equals(GameState.INITIATING)) {
            getBoard().getEntities().clear();
            LevelReader.loadLevel("levels.txt", this, ++level);
        }
        getGameFrame().setView(new GameGuiView(getBoard()));
        getGameFrame().addView(getGameFrame().getView(),
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
        getGameFrame().revalidate();
        getGameFrame().repaint();
        getGameFrame().requestFocusInWindow();
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
                .filter(entity -> entity.getEntityType().equals(SokobanEntityType.GROUND_MARKED))
                .map(entity -> (Tile) entity)
                .collect(Collectors.toList());
    }

    @Override
    public void pause() {
        getGameFrame().addView(new PauseMenu(this), 970, 640);
    }

    public void gameOver() {
        getGameFrame().addView(new GameOverScreen(this), 970, 640);
    }
}
