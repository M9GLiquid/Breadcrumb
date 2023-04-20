package eu.kingconquest.sokoban.audio;

import eu.kingconquest.framework.audio.AudioType;

public class SokobanAudioType extends AudioType {
    public static final SokobanAudioType CRATE_AUDIO = new SokobanAudioType("crate_move.wav", 0.5f);
    public static final SokobanAudioType PLAYER_AUDIO = new SokobanAudioType("player_move.wav", 0.7f);

    private SokobanAudioType(String audioPath, float audioVolume) {
        super(audioPath, audioVolume);
    }
}
