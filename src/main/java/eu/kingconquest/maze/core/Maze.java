package eu.kingconquest.maze.core;

import eu.kingconquest.framework.controllers.GuiController;
import eu.kingconquest.framework.controllers.KeyController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.core.StateManager;
import eu.kingconquest.framework.entities.Entity;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.ui.*;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.ConsoleView;
import eu.kingconquest.framework.views.GraphicalView;
import eu.kingconquest.maze.audio.MazeAudioObserver;
import eu.kingconquest.maze.entities.MazeEntityIcon;
import eu.kingconquest.maze.entities.MazePlayer;
import eu.kingconquest.maze.io.MazeLevelReader;
import eu.kingconquest.maze.models.MazeBoard;
import eu.kingconquest.maze.ui.MazeGameOver;
import eu.kingconquest.maze.ui.MazeWinScreen;

import javax.swing.*;

public class Maze extends Game {

    public boolean guiController = false;
    public int level;

    public Maze(GameFrame gameFrame){
        super(gameFrame, "Maze");

    }
    @Override
    public void initiate() {
      setBoard(new MazeBoard(this, null, null));

      setDesiredController(new KeyController(getBoard()));
      GameFrame frame = getGameFrame();
      frame.setGameView((new GraphicalView(getBoard())));

        //Setup of the GameView
        frame.addView(new StartMenu(this), Menu.WIDTH, Menu.HEIGHT);

        // State Observers
        getController().addStateObserver(new StateManager(this));
        // Audio Observers
        getController().addAudioObserver(new MazeAudioObserver());
        // View Observers
        getController().addViewObserver(frame.getGameView());
        getController().addViewObserver(new ConsoleView(getBoard()));

        getBoard().setState(GameState.INITIATE);

        //32 for smaller walls
        Tile.setTileSize(64);

        level = -1;
    }


    @Override
    public void start() {
        if(nextLevel())
            return;
        getBoard().setState(GameState.RUN);

        if(getController() instanceof GuiController && !guiController){
            new FloatingBtnsView(getGameFrame(), (GuiController) getController());
            guiController = true;
        }
    }


    protected boolean nextLevel(){
        //Get state of the game
        GameState gameState = getBoard().getState();

        if(gameState.equals(GameState.LEVEL_COMPLETE) ||
                gameState.equals(GameState.INITIATE) ||
                gameState.equals(GameState.RESET)){

            // Clear entities for the different statements
            getBoard().getEntities().clear();

            // Get next level
            int load = MazeLevelReader.loadLevel("maze-lvl.txt", getBoard(), ++level);

            // If max level reached
            if(load == 1){
                getBoard().setState(GameState.WIN);
                end();
                return true;
            }
        }
        setGameView();

        return true;
    }

    @Override
    public void end() {
        if(getBoard().getState().equals(GameState.GAME_OVER)) {
              getGameFrame().addView(new MazeGameOver(this), Menu.WIDTH, Menu.HEIGHT);
        }
        else{
            getGameFrame().addView(new MazeWinScreen(this), Menu.WIDTH, Menu.HEIGHT);
        }
    }


    @Override
    public void restart() {
        getBoard().setState(GameState.RESET);
        level--;
        start();
    }

    @Override
    public void save() {
        setGameData(new GameData(getBoard().grid, getBoard().getEntities(), level));

        // Save game
        String message = DataWriter.save(this);

        // Send notification
        Notification.showNotification(this, message);
    }

    @Override
    public void load() {
        String message = DataReader.load(this);

        if(message.equals("Game Loading!")){
            setData();
            Timer timer = new Timer(1500, e -> {
                getBoard().setState(GameState.RUN);
                setGameView();
            });
            timer.setRepeats(false);
            timer.start();

        }
        //Showing message on screen
        Notification.showNotification(this, message);
    }

    @Override
    public void pause() {
        getGameFrame().addView(new PauseMenu(this), Menu.WIDTH, Menu.HEIGHT);
    }

    protected void setData(){
        //Storing the data
        level = getGameData().level;
        getBoard().grid = getGameData().grid;
        getBoard().setEntities(getGameData().entities);
        getBoard().COLS = getBoard().grid[0].length;
        getBoard().ROWS = getBoard().grid.length;
    }


    protected void setGameView(){
        getGameFrame().addView(getGameFrame().getGameView(), getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
    }

    public MazePlayer getPlayer(){
        return (MazePlayer) getBoard().getEntities().stream()
                .filter(entity -> entity instanceof MazePlayer)
                .findFirst()
                .orElse(null);
    }

    public Entity getGoal() {
        return getBoard().getEntities().stream()
                .filter(entity -> entity.getEntityType().equals(MazeEntityIcon.MAZE_GROUND_MARKED))
                .findFirst()
                .orElse(null);
    }
}
