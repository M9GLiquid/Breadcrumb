package eu.kingconquest.framework.Listener;

import eu.kingconquest.framework.controllers.GuiController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.views.FloatingButtonsView;
import eu.kingconquest.framework.views.GameGuiView;
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
        // Remove all the observers.
        game.getController().removeStateObservers();
        game.getController().removeAudioObservers();
    }


    private void save(){
        game.save();
    }

    private void load(){
        game.load();
    }

    private void start(){
        GameBoard board = game.getBoard();
        board.setState(GameState.INITIATING);
        game.getController().notifyStateObservers();
        GameGuiView gameGuiView = new GameGuiView(board);
        game.getController().addViewObserver(gameGuiView);
        game.getGameFrame().addView(gameGuiView,
                board.COLS * Tile.getTileSize(),
                board.ROWS * Tile.getTileSize());
        game.getGameFrame().setView(gameGuiView);

        if (game.getController() instanceof GuiController)
            new FloatingButtonsView(game.getGameFrame(), game.getController());
        game.start();
    }


    private void back() {
        game.getGameFrame().remove(view);
        game.getGameFrame().addView(new StartMenu(game), 970, 640);
    }

    private void resume(){
        GameFrame gameFrame = game.getGameFrame();
        gameFrame.remove(view);
        game.getBoard().setState(GameState.RUNNING);
        gameFrame.addView(gameFrame.getView(),
                game.getBoard().COLS * Tile.getTileSize(),
                game.getBoard().ROWS * Tile.getTileSize());
    }

}
