package eu.kingconquest.framework.audio;

import eu.kingconquest.framework.entity.Entity;

/**
 * The AudioObserver interface represents a way to update audio-related components
 * when a particular event occurs in the game.
 * <p>
 * Implementations of this interface should define how the audio should be handled
 * when the update method is called.
 * </p>
 * @author Thomas Lundqvist
 * @version 1.0
 */
public interface AudioObserver {

    /**
     * Updates the audio component based on the provided entity.
     *
     * @param entity The entity associated with the event that triggered the update.
     */
    void update(Entity entity);
}