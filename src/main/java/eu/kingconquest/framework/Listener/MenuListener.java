package eu.kingconquest.framework.Listener;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.platform.PlatformMenu;
import eu.kingconquest.platform.listeners.PlatformListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener extends PlatformListener implements ActionListener {
    private final Game game;
    private final JPanel view;

    private final MenuItem menuItem;

    public MenuListener(Game game, JPanel view, MenuItem menuItem){
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
            case MAIN_MENU -> back();
            case PLATFORM -> platform();
            case RESUME -> resume();
            case RESTART -> restart();
            case EXIT -> exit();
        }
    }

    private void restart() {
        game.restart();
    }

    /**
     * Go back to the Game Selector
     */
    private void platform() {
        game.getGameFrame().addView(new PlatformMenu(game.getGameFrame()), 970, 640);

        // Clear all the observers.
        game.getController().clearStateObservers();
        game.getController().clearAudioObservers();
        game.getController().clearViewObservers();
    }


    private void save(){
        game.save();
    }

    private void load(){
        game.load();
    }

    private void start(){
        game.start();
    }


    private void back() {
        game.getBoard().setState(GameState.INITIATING);
        game.getGameFrame().remove(view);
        game.getGameFrame().addView(new StartMenu(game), 970, 640);
        game.getController().notifyStateObservers();
    }

    private void resume(){
        GameFrame gameFrame = game.getGameFrame();
        gameFrame.remove(view);
        game.getBoard().setState(GameState.RUNNING);
        gameFrame.addView(gameFrame.getPreviousView(),
                game.getBoard().COLS * Tile.getTileSize(),
                game.getBoard().ROWS * Tile.getTileSize());
    }

}
