package eu.kingconquest.framework.Listener;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.framework.ui.StartMenu;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.platform.PlatformMenu;
import eu.kingconquest.platform.listeners.PlatformListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuListener class extends the PlatformListener and implements the ActionListener.
 * It listens to the events triggered in the game's menu and performs corresponding actions.
 */
public class MenuListener extends PlatformListener implements ActionListener {
    private final Game game;
    private final JPanel view;

    /**
     * Constructs a MenuListener with the specified game instance and the game view.
     *
     * @param game the game instance that this listener will handle
     * @param view the JPanel object representing the game view
     */
    public MenuListener(Game game, JPanel view){
        super(game.getGameFrame(), view);
        this.game = game;
        this.view = view;
    }

    /**
     * Executes different actions based on the commands associated with the action events.
     *
     * @param event the action event object containing information about the event source
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand().toUpperCase()){
            case "SAVE" -> save();
            case "LOAD" -> load();
            case "START" -> start();
            case "MAIN MENU" -> back();
            case "PLATFORM" -> platform();
            case "RESUME" -> resume();
            case "RESTART" -> restart();
            case "EXIT" -> exit();
        }
    }

    /**
     * Restarts the current game.
     */
    private void restart() {
        game.restart();
    }

    /**
     * Go back to the Game Selector
     * and clear all the observers of the game
     */
    private void platform() {
        game.getGameFrame().addView(new PlatformMenu(game.getGameFrame()), 970, 640);

        // Clear all the observers.
        game.getController().clearStateObservers();
        game.getController().clearAudioObservers();
        game.getController().clearViewObservers();
    }

    /**
     * Saves the current game state.
     */
    private void save(){
        game.save();
    }

    /**
     * Loads a previously saved game state.
     */
    private void load(){
        game.load();
    }

    /**
     * Starts the game.
     */
    private void start(){
        game.start();
    }

    /**
     * Takes the user back to the game initiation state and shows the start menu.
     */
    private void back() {
        game.getBoard().setState(GameState.INITIATING);
        game.getGameFrame().remove(view);
        game.getGameFrame().addView(new StartMenu(game), Menu.WIDTH, Menu.HEIGHT);
        game.getController().notifyStateObservers();
    }

    /**
     * Resumes the paused game by changing the current to previous view and changing the game state to RUNNING.
     */
    private void resume(){
        GameFrame gameFrame = game.getGameFrame();
        gameFrame.remove(view);
        game.getBoard().setState(GameState.RUNNING);
        gameFrame.addView(gameFrame.getPreviousView(),
                game.getBoard().COLS * Tile.getTileSize(),
                game.getBoard().ROWS * Tile.getTileSize());
    }

}
