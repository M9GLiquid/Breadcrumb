package eu.kingconquest.maze.core;

import eu.kingconquest.framework.controllers.KeyBoardController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.observers.ConsoleViewObserver;
import eu.kingconquest.framework.observers.StateObserver;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.framework.ui.Notification;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.GameGuiView;
import eu.kingconquest.maze.models.MazeBoard;

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

      setDesiredController(new KeyBoardController(getBoard()));
      GameFrame frame = getGameFrame();
      frame.setGameView((new GameGuiView(getBoard())));

        //Setup of the GameView
        frame.addView(new StartMenu(this), Menu.WIDTH, Menu.HEIGHT);

        // State Observers
        getController().addStateObserver(new StateObserver(this));
        // Audio Observers
        //getController().addAudioObserver(new (this));
        // View Observers
        getController().addViewObserver(frame.getGameView());
        getController().addViewObserver(new ConsoleViewObserver(getBoard()));

        getBoard().setState(GameState.INITIATING);

        //32 for smaller walls
        Tile.setTileSize(32);

        level = -1;

    }

    @Override
    public void start() {

    }


    protected boolean nextLevel(){
        //Get state of the game
        GameState gameState = getBoard().getState();

        if(gameState.equals(GameState.LEVEL_COMPLETE) || gameState.equals(GameState.INITIATING)||
        gameState.equals(GameState.RESETTING)){

            // Clear entities for the different statements
            getBoard().getEntities().clear();

            //loading next level

        }

        return true;
    }

    @Override
    public void gameOver() {


    }

    @Override
    public void restart() {

    }

    @Override
    public void save() {

    }

    @Override
    public void load() {
        String message = DataReader.load(this);

        if(message.equals("Game Loading!")){
            setData();
            Timer mazetimer = new Timer(1500, e -> {
                getBoard().setState(GameState.RUNNING);
                setGameView();
            });
            mazetimer.setRepeats(false);
            mazetimer.start();

        }
        //Showing message on screen
        Notification.showNotification(this, message);
    }

    @Override
    public void pause() {

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
}
