package eu.kingconquest.framework.observers;

/**
 * The GameViewObserver interface should be implemented by any class whose instances are intended to be updated
 * whenever changes occur to the game view. An observer is added to the game view by calling the game's
 * addViewObserver method.
 */
public interface GameViewObserver {

    /**
     * Invoked when a change to the game state occurs that needs to be reflected in the view.
     */
    void update();
}
