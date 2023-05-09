package eu.kingconquest.sokoban.audio;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.sokoban.core.SokobanEntityType;

public class SokobanGameAudioObserver implements GameAudioObserver {
    @Override
    public void update(Entity entity) {
        SokobanAudioType audioType = null;

        if (entity.getEntityType().equals(SokobanEntityType.CRATE))
            audioType = SokobanAudioType.CRATE_AUDIO;
        else if (entity.getEntityType().equals(SokobanEntityType.PLAYER))
            audioType = SokobanAudioType.PLAYER_AUDIO;

        if (audioType != null)
            audioType.play();
    }

}
