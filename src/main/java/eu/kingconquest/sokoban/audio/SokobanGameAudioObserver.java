package eu.kingconquest.sokoban.audio;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.sokoban.entities.SokobanEntityIcon;

public class SokobanGameAudioObserver implements GameAudioObserver {

    private final SokobanAudioManager crateAudio;
    private final SokobanAudioManager playerAudio;

    public SokobanGameAudioObserver(SokobanAudioManager crateAudio, SokobanAudioManager playerAudio) {
        this.crateAudio = crateAudio;
        this.playerAudio = playerAudio;
    }
    @Override
    public void update(Entity entity) {
        SokobanAudioManager audioType = null;

        if (entity.getEntityType().equals(SokobanEntityIcon.CRATE))
            audioType = crateAudio;
        else if (entity.getEntityType().equals(SokobanEntityIcon.PLAYER))
            audioType = playerAudio;

        if (audioType != null)
            audioType.play();
    }
}
