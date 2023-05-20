package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.framework.observers.GameStateObserver;
import eu.kingconquest.framework.observers.GameViewObserver;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.utils.Location;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * The GuiController class provides methods to manage and control the game user interface.
 * It contains state, view, and audio observers that react to changes in the game.
 * It implements the GameStrategy interface.
 */
public class GuiController implements GameStrategy {
    final GameBoard gameBoard;
    final Set<GameStateObserver> stateObservers = new HashSet<>();
    final Set<GameViewObserver> viewObservers = new HashSet<>();
    final Set<GameAudioObserver> audioObservers = new HashSet<>();

    /**
     * Instantiates a new GuiController.
     *
     * @param gameBoard the game board instance
     */
    public GuiController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    @Override
    public void addAudioObserver(GameAudioObserver observer) {
        audioObservers.removeIf(obs -> obs.getClass().equals(observer.getClass()));
        audioObservers.add(observer);
    }

    @Override
    public void addStateObserver(GameStateObserver observer) {
        stateObservers.removeIf(obs -> obs.getClass().equals(observer.getClass()));
        stateObservers.add(observer);
    }
    @Override
    public void addViewObserver(GameViewObserver observer) {
        viewObservers.removeIf(obs -> obs.getClass().equals(observer.getClass()));
        viewObservers.add(observer);
    }
    @Override
    public void notifyStateObservers() {
        for (GameStateObserver observer : stateObservers)
            observer.update();
    }

    @Override
    public void notifyViewObservers() {
        for (GameViewObserver observer : viewObservers)
            observer.update();
    }

    @Override
    public void notifyAudioObservers() {
        for (GameAudioObserver observer : audioObservers)
            observer.update();
    }

    @Override
    public void clearStateObservers() {
        stateObservers.clear();
    }

    @Override
    public void clearAudioObservers() {
        audioObservers.clear();
    }

    @Override
    public void clearViewObservers() {
        viewObservers.clear();
    }


    /**
     * Handles user key input and updates the game accordingly.
     * Based on the event input sets the direction and updates the state/view observers
     * This method is automatically called when a key is pressed.
     *
     * @param event the event to be handled
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Location direction = new Location(0, 0);

        // Move the direction of the button pressed
        String command = event.getActionCommand();

        switch (command) {
            case "UP" -> direction.setY(-1);
            case "RIGHT" -> direction.setX(1);
            case "DOWN" -> direction.setY(1);
            case "LEFT" -> direction.setX(-1);
        }

        if (direction.getX() != 0 || direction.getY() != 0 && gameBoard.getState().equals(GameState.RUNNING))
            gameBoard.makeMove(direction);

        notifyStateObservers();
        notifyViewObservers();
    }

    /**
     * Handles key typed events.
     *
     * @param event the key event
     */
    @Override
    public void keyTyped(KeyEvent event) {

    }

    /**
     * Handles key pressed events and updates the observers.
     *
     * @param event the key event
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
            gameBoard.setState(GameState.PAUSED);

        notifyStateObservers();
    }

    /**
     * Handles key released events.
     *
     * @param event the key event
     */
    @Override
    public void keyReleased(KeyEvent event) {

    }
}
