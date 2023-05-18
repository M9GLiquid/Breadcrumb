package eu.kingconquest.twozerofoureight.core;

import eu.kingconquest.framework.controllers.KeyBoardController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.observers.StateObserver;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.PauseMenu;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.GameGuiView;
import eu.kingconquest.twozerofoureight.models.Board2048;

public class Game2048 extends Game {
    public Game2048(GameFrame gameframe) {
        super(gameframe,"2048");

        // Game Board setup
        setBoard(new Board2048(this, 6, 6));
        getBoard().setState(GameState.INITIATING);

        // Set the desired controller
        setDesiredController(new KeyBoardController(getBoard()));


        // Game View setup
        getGameFrame().addView(new StartMenu(this), 970, 640);

        // Game Observers Setup
        getController().addStateObserver(new StateObserver(this));
        getController().notifyStateObservers();

        Tile.setTileSize(64);
    }

    @Override
    public void initiate() {
        for(int col = 0; col < getBoard().COLS; col++){
            for(int row = 0; row < getBoard().ROWS; row++){
                if(row < 1 || col < 1 || row == 5 || col == 5)
                    getBoard().grid[col][row] = new Tile(new Location(col, row), EntityType2048.WALL, false);
                else
                    getBoard().grid[col][row] = new Tile(new Location(col, row), EntityType2048.GROUND, true);
            }
        }
        getBoard().setState(GameState.INITIATING);
    }

    @Override
    public void gameOver() {
        getBoard().setState(GameState.GAME_OVER);
    }

    @Override
    public void start() {
        getBoard().getEntities().clear();
        Board2048 board = (Board2048) getBoard();
        board.addNewTile();
        board.addNewTile();
        startGame();
        getBoard().setState(GameState.RUNNING);
    }

    @Override
    public void restart() {
        start();
    }


    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public void pause() {
        getGameFrame().addView(new PauseMenu(this), 970, 640);
    }

    public void startGame() {
        GameGuiView view = new GameGuiView(getBoard());
        getGameFrame().addView(view,
                getBoard().COLS * Tile.getTileSize(),
                getBoard().ROWS * Tile.getTileSize());
        getController().addViewObserver(view);
    }
}
