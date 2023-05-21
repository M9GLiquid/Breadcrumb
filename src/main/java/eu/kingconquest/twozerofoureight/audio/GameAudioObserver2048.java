package eu.kingconquest.twozerofoureight.audio;

import eu.kingconquest.framework.observers.GameObserver;

public class GameAudioObserver2048 implements GameObserver {
    /**
     * The GameAudioObserver2048 class implements the GameAudioObserver interface.
     * It is responsible for updating the game audio based on the game entities.
     */
    public GameAudioObserver2048() {
    }

    /**
     * Updates the game audio.
     */
    @Override
    public void update() {
        AudioType2048 audioType = AudioType2048.CRATE_AUDIO;
        audioType.play();
    }
}
