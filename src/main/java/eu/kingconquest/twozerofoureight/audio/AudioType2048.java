package eu.kingconquest.twozerofoureight.audio;

import eu.kingconquest.framework.audio.AudioManager;

/**
 * The 'AudioType2048' class represents an audio type for the game 2048. It extends the 'AudioManager' class.
 */
public class AudioType2048 extends AudioManager {
    public static final AudioType2048 CRATE_AUDIO = new AudioType2048("game2048/audio/crate_move.wav", 0.8f);

    /**
     * Constructs a new AudioType2048 object.
     * @param audioPath The path to the audio file.
     * @param audioVolume The volume level of the audio.
     */
    private AudioType2048(String audioPath, float audioVolume) {
        super(audioPath, audioVolume);
    }
}

