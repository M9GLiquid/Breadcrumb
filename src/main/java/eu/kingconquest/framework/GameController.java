package eu.kingconquest.framework;

import eu.kingconquest.framework.utils.Location;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a GameController class that handles user input and game updates.
 * The GameController also manages a list of observers to be notified of game changes.
 */
public class GameController extends KeyAdapter implements GameStrategy {
    private final Game game;
    private final List<GameObserver> observers;

    /**
     * Creates a GameController for the specified game.
     *
     * @param game the game to be controlled
     */
    public GameController(Game game) {
        this.game = game;
        observers = new ArrayList<>();
    }

    /**
     * Adds a GameObserver to the list of observers.
     *
     * @param observer the GameObserver to be added
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes a GameObserver from the list of observers.
     *
     * @param observer the GameObserver to be removed
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of a change in the game state.
     */
    public void notifyObservers() {
        for (GameObserver observer: observers)
            observer.update();
    }

    /**
     * Handles user key input and updates the game accordingly.
     * This method is automatically called when a key is pressed.
     *
     * @param event the KeyEvent containing information about the key event
     */
    @Override
    public void keyPressed(KeyEvent event) {
        Location location = new Location(0, 0);

        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> location.setY(-1);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> location.setY(1);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> location.setX(-1);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> location.setX(1);
        }

        if (location.getX() != 0 || location.getY() != 0)
            game.getBoard().makeMove(location);
    }
}
