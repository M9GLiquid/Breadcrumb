package eu.kingconquest.maze.audio;

import eu.kingconquest.framework.audio.AudioManager;

public class MazeAudioManager extends AudioManager {

    //TODO: Bad Practise, need to refactor
    public static final MazeAudioManager PLAYER_AUDIO = new MazeAudioManager("sokoban/audio/player_move.wav", 1f);

    private MazeAudioManager(String audioPath, float audioVolume) {
        super(audioPath, audioVolume);
    }
}
