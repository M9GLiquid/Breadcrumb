package eu.kingconquest.sokoban.Listener;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.GameView;
import eu.kingconquest.framework.ui.View;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.platform.PlatformMenu;
import eu.kingconquest.sokoban.ui.StartMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener extends PlatformListener implements ActionListener {
    private final Game game;
    private final View view;

    private final MenuItem menuItem;

    public MenuListener(Game game, View view, MenuItem menuItem){
        super(game.getGameFrame(), view, menuItem);
        this.game = game;
        this.view = view;
        this.menuItem = menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(menuItem){
            case SAVE -> save();
            case LOAD -> load();
            case START -> start();
            case RETURN -> back();
            case PLATFORM -> platform();
            case RESUME -> resume();
            case EXIT -> exit();
        }
    }

    /**
     * Go back to the Game Selector
     */
    private void platform() {
        game.getGameFrame().addView(new PlatformMenu(game.getGameFrame()), 970, 640);
    }


    private void save(){
        game.save();
    }

    private void load(){
        game.load();
    }

    private void start(){
        game.setState(GameState.INITIATING);
        game.getController().notifyObservers();
        GameView gameView = new GameView(game);
        game.getGameFrame().addView(gameView,
                game.getBoard().COLS * Tile.getTileSize(),
                game.getBoard().ROWS * Tile.getTileSize());
        game.getGameFrame().setView(gameView);

        game.start();
    }


    private void back() {
        game.getGameFrame().remove(view);
        game.getGameFrame().addView(new StartMenu(game), 970, 640);
    }

    private void resume(){
        GameFrame gameFrame = game.getGameFrame();
        gameFrame.remove(view);
        game.setState(GameState.RUNNING);
        gameFrame.addView(gameFrame.getView(),
                game.getBoard().COLS * Tile.getTileSize(),
                game.getBoard().ROWS * Tile.getTileSize());
    }

}
