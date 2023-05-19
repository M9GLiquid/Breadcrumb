package eu.kingconquest.twozerofoureight.core;

import eu.kingconquest.framework.controllers.KeyBoardController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.observers.StateObserver;
import eu.kingconquest.framework.ui.*;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.GameGuiView;
import eu.kingconquest.twozerofoureight.ui.GameOverScreen;
import eu.kingconquest.twozerofoureight.ui.WinScreen;
import eu.kingconquest.twozerofoureight.models.Board2048;

import javax.swing.*;

public class Game2048 extends Game {

    public boolean hasWon = false;
    public Game2048(GameFrame gameframe) {
        super(gameframe,"2048");

        // Game Board setup
        setBoard(new Board2048(this, 6, 6));
        getBoard().setState(GameState.INITIATING);

        // Set the desired controller
        setDesiredController(new KeyBoardController(getBoard()));


        GameFrame frame = getGameFrame();
        frame.setGameView(new GameGuiView(getBoard()));

        // Game View setup
        frame.addView(new StartMenu(this), 970, 640);
        getController().addViewObserver(frame.getGameView());

        // Game Observers Setup
        getController().addStateObserver(new StateObserver(this));
        getController().notifyStateObservers();

        Tile.setTileSize(64);
    }

    @Override
    public void initiate() {
        buildBoard();
        getBoard().setState(GameState.INITIATING);
    }

    public void buildBoard(){
        for(int col = 0; col < getBoard().COLS; col++){
            for(int row = 0; row < getBoard().ROWS; row++){
                if(row < 1 || col < 1 || row == 5 || col == 5)
                    getBoard().grid[col][row] = new Tile(new Location(col, row), EntityType2048.WALL, false);
                else
                    getBoard().grid[col][row] = new Tile(new Location(col, row), EntityType2048.GROUND, true);
            }
        }
    }

    @Override
    public void gameOver() {
        Board2048 board = (Board2048) getBoard();
        if (getBoard().getState().equals(GameState.GAME_OVER))
            getGameFrame().addView(new GameOverScreen(this, board.getScore()), Menu.WIDTH, Menu.HEIGHT);
        else
            getGameFrame().addView(new WinScreen(this, board.getScore()), Menu.WIDTH, Menu.HEIGHT);
    }

    @Override
    public void start() {
        getBoard().getEntities().clear();
        Board2048 board = (Board2048) getBoard();
        board.addNewTile();
        board.addNewTile();
        startGame();
        getBoard().setState(GameState.RUNNING);
        ((Board2048) getBoard()).resetScore();
        hasWon = false;
    }

    @Override
    public void restart() {
        getBoard().setState(GameState.RESETTING);
        start();
    }


    @Override
    public void save() {
        // Upload game data
        setGameData(new GameData(getBoard().grid, getBoard().getEntities(), 0));

        // Try save
        String message = DataWriter.save(this);

        // Show notification
        Notification.showNotification(this, message);
    }

    @Override
    public void load() {
        String message = DataReader.load(this);

        if (message.equals("Game loaded successfully!")) {
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

    protected void setGameView(){
        getGameFrame().addView(getGameFrame().getGameView(),
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
    }

    public void setData(){
        getBoard().grid = getGameData().grid;
        getBoard().setEntities(getGameData().entities);
        getBoard().COLS = getBoard().grid[0].length;
        getBoard().ROWS = getBoard().grid.length;
    }

    @Override
    public void pause() {
        getGameFrame().addView(new PauseMenu(this), 970, 640);
    }

    public void startGame() {
        getGameFrame().addView(getGameFrame().getGameView(),
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
    }
}
