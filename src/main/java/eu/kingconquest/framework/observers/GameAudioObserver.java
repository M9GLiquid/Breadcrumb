package eu.kingconquest.framework.observers;

/**
 * The AudioObserver interface represents a way to update audio-related components
 * when a particular event occurs in the game.
 * <p>
 * Implementations of this interface should define how the audio should be handled
 * when the update method is called.
 * </p>
 */
public interface GameAudioObserver {

    /**
     * Updates the audio component based on the provided entity.
     */
    void update();
}