package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.observers.AudioObserver;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.observers.GameStateObserver;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.observers.GameViewObserver;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameController class handles user input and game updates for a specified game.
 * It also manages a list of observers to be notified of game changes and audio updates.
 */
public class KeyBoardController implements GameStrategy {
    private final GameBoard gameBoard;
    private final List<GameStateObserver> stateObservers = new ArrayList<>();
    private final List<GameViewObserver> viewObservers = new ArrayList<>();
    private final List<AudioObserver> audioObservers = new ArrayList<>();

    /**
     * Creates a GameController for the specified game.
     *
     * @param gameBoard the game to be controlled
     */
    public KeyBoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void addAudioObserver(AudioObserver observer) {
        audioObservers.add(observer);
    }

    /**
     * Adds a GameObserver to the list of observers.
     *
     * @param observer the GameObserver to be added
     */
    public void addStateObserver(GameStateObserver observer) {
        stateObservers.add(observer);
    }

    @Override
    public void addViewObserver(GameViewObserver observer) {
        viewObservers.add(observer);
    }

    /**
     * Notifies all registered observers of a change in the game state.
     */
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
    public void notifyAudioObservers(Entity entity) {
        for (AudioObserver observer : audioObservers)
            observer.update(entity);
    }

    @Override
    public void removeStateObservers() {
        stateObservers.clear();
    }

    @Override
    public void removeAudioObservers() {
        audioObservers.clear();
    }

    @Override
    public void removeGameViewObservers() {
        viewObservers.clear();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles user key input and updates the game accordingly.
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

        if (direction.getX() != 0 || direction.getY() != 0 && gameBoard.getState().equals(GameState.RUNNING))
            gameBoard.makeMove(direction);

        notifyStateObservers();
        notifyViewObservers();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
