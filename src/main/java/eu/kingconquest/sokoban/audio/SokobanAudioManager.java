package eu.kingconquest.sokoban.audio;

import eu.kingconquest.framework.audio.AudioManager;

public class SokobanAudioManager extends AudioManager {

    //TODO: Bad Practise, need to refactor
    public static final SokobanAudioManager CRATE_AUDIO = new SokobanAudioManager("sokoban/audio/crate_move.wav", 0.5f);
    public static final SokobanAudioManager PLAYER_AUDIO = new SokobanAudioManager("sokoban/audio/player_move.wav", 0.7f);

    private SokobanAudioManager(String audioPath, float audioVolume) {
        super(audioPath, audioVolume);
    }
}
