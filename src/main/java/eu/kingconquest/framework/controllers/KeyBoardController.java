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
 * The KeyBoardController class handles user key input and game updates for a specified game.
 * It also manages a list of observers to be notified of game changes and audio updates.
 */
public class KeyBoardController implements GameStrategy {
    private final GameBoard gameBoard;
    private final Set<GameStateObserver> stateObservers = new HashSet<>();
    private final Set<GameViewObserver> viewObservers = new HashSet<>();
    private final Set<GameAudioObserver> audioObservers = new HashSet<>();

    /**
     * Creates a KeyBoardController for the specified game.
     *
     * @param gameBoard the game to be controlled
     */
    public KeyBoardController(GameBoard gameBoard) {
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


    public void notifyStateObservers() {
        for (GameStateObserver observer: stateObservers)
            observer.update();
    }

    @Override
    public void notifyViewObservers() {
        for (GameViewObserver observer: viewObservers)
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
     * Handles key typed events.
     *
     * @param event the key event
     */
    @Override
    public void keyTyped(KeyEvent event) {
    }

    /**
     * Handles user key input and updates the game accordingly.
     * Based on the event input sets the direction and updates the state/view observers
     * This method is automatically called when a key is pressed.
     *
     * @param event the KeyEvent containing information about the key event
     */
    @Override
    public void keyPressed(KeyEvent event) {
        Location direction = new Location(0, 0);

        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> direction.setY(-1);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> direction.setY(1);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> direction.setX(-1);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> direction.setX(1);
            case KeyEvent.VK_ESCAPE -> gameBoard.setState(GameState.PAUSED);
        }

        if ((direction.getX() != 0 || direction.getY() != 0) && gameBoard.getState().equals(GameState.RUNNING))
            gameBoard.makeMove(direction);

        notifyStateObservers();
        notifyViewObservers();
    }

    /**
     * Handles key released events.
     *
     * @param event the key event
     */
    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }
}
