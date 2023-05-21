package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.utils.Location;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyBoardController class handles user key input and game updates for a specified game.
 * It also manages a list of observers to be notified of game changes and audio updates.
 */
public class KeyController extends GameStrategy implements KeyListener {
    private final GameBoard gameBoard;

    /**
     * Creates a KeyBoardController for the specified game.
     *
     * @param gameBoard the game to be controlled
     */
    public KeyController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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
            case KeyEvent.VK_ESCAPE -> {
                if (gameBoard.getState() == GameState.RUNNING)
                    gameBoard.setState(GameState.PAUSED);
            }
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
}
