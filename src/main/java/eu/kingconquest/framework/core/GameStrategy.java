package eu.kingconquest.framework.core;

import eu.kingconquest.framework.audio.AudioObserver;
import eu.kingconquest.framework.entity.Entity;

/**
 * Interface for game strategies that handle observer registration, removal, and notifications.
 * This interface defines methods for managing GameObserver and AudioObserver instances
 * to be notified of changes in the game state and audio events.
 */
public interface GameStrategy {

    /**
     * Adds a GameObserver to the list of observers.
     *
     * @param observer the GameObserver to be added
     */
    void addObserver(GameObserver observer);

    /**
     * Adds an AudioObserver to the list of audio observers.
     *
     * @param observer the AudioObserver to be added
     */
    void addAudioObserver(AudioObserver observer);

    /**
     * Removes a GameObserver from the list of observers.
     *
     * @param observer the GameObserver to be removed
     */
    void removeObserver(GameObserver observer);

    /**
     * Removes an AudioObserver from the list of audio observers.
     *
     * @param observer the AudioObserver to be removed
     */
    void removeAudioObserver(AudioObserver observer);

    /**
     * <p>
     * Notifies all registered GameObservers of a change in the game state.
     * This method is typically called when there is a significant update in the game,
     * such as player or crate movement, level completion, or game reset.
     * </p>
     * <p>
     * The registered GameObservers will be responsible for updating the game's state
     * and triggering appropriate actions based on the current state.
     * </p>
     * <p>
     * For example, in the Sokoban game, when the player moves, the controller calls
     * this method to notify the StateObserver, which then updates the game state
     * accordingly (e.g., setting the game state to LEVEL_COMPLETE if the level is complete).
     * </p>
     */
    void notifyObservers();

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
}
