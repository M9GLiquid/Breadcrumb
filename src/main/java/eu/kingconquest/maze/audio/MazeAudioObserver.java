package eu.kingconquest.maze.audio;

import eu.kingconquest.framework.observers.GameObserver;

public class MazeAudioObserver implements GameObserver {

    @Override
    public void update() {
            MazeAudioManager.PLAYER_AUDIO.play();
    }
}
