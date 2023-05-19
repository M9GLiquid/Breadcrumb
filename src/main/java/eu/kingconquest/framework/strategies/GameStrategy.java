package eu.kingconquest.framework.strategies;

import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.framework.observers.GameStateObserver;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.observers.GameViewObserver;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Interface for game strategies that handle observer registration, removal, and notifications.
 * This interface defines methods for managing GameObserver and AudioObserver instances
 * to be notified of changes in the game state and audio events.
 */
public interface GameStrategy extends ActionListener, KeyListener{

    /**
     * Adds a GameObserver to the list of observers.
     *
     * @param observer the GameObserver to be added
     */
    void addStateObserver(GameStateObserver observer);

    /**
     * Adds a GameViewObserver to the list of observers.
     *
     * @param observer the GameViewObserver to be added
     */
    void addViewObserver(GameViewObserver observer);

    /**
     * Adds an AudioObserver to the list of audio observers.
     *
     * @param observer the AudioObserver to be added
     */
    void addAudioObserver(GameAudioObserver observer);

    /**
     * <p>
     * Notifies all registered GameStateObservers of a change in the game state.
     * This method is typically called when there is a significant update in the game,
     * such as player or crate movement, level completion, or game reset.
     * </p>
     * <p>
     * The registered GameStateObservers will be responsible for updating the game's state
     * and triggering appropriate actions based on the current state.
     * </p>
     * <p>
     * For example, in the Sokoban game, when the player moves, the controller calls
     * this method to notify the StateObserver, which then updates the game state
     * accordingly (e.g., setting the game state to LEVEL_COMPLETE if the level is complete).
     * </p>
     */
    void notifyStateObservers();

    /**
     * <p>
     * Notifies all registered GameViewObservers of a change in the game state.
     * This method is typically called when there is a significant update in the game,
     * such as player or crate movement, level completion, or game reset.
     * </p>
     * <p>
     * The registered GameViewObservers will be responsible for updating the game's state
     * and triggering appropriate actions based on the current state.
     * </p>
     * <p>
     * For example, in the Sokoban game, when the player moves, the controller calls
     * this method to notify the ViewObserver, which then updates the game views
     * accordingly (e.g., repainting GUI's or writing to console).
     * </p>
     */
    void notifyViewObservers();

    /**
     * <p>
     * Notifies all registered AudioObservers of an update related to an Entity.
     * This method is typically called when there is a significant event concerning
     * the entity, such as a player or a crate being moved in the game.
     * </p>
     * <p>
     * The registered AudioObservers will be responsible for playing the appropriate
     * audio based on the type of the given entity (e.g., player or crate).
     * </p>
     * @param entity the Entity instance for which the update occurred, such as a moved player or crate
     */
    void notifyAudioObservers(Entity entity);

    /**
     * Removes all registered GameStateObservers.
     * This method is typically called when the game is about to end or restart,
     * and there's no need for the current GameStateObservers anymore.
     */
    void clearStateObservers();

    /**
     * Removes all registered GameAudioObservers.
     * This method is typically called when the game is about to end or restart,
     * and there's no need for the current GameAudioObservers anymore.
     */
    void clearAudioObservers();

    /**
     * Removes all registered GameViewObservers.
     * This method is typically called when the game is about to end or restart,
     * and there's no need for the current GameViewObservers anymore.
     */
    void clearViewObservers();
}
