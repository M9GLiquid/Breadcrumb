package eu.kingconquest.framework;

/**
 * The GameObserver interface defines an update method for objects that need to be notified
 * of changes in the game state.
 */
public interface GameObserver{

    /**
     * Updates the observer with the current state of the game.
     */
    void update();
}
