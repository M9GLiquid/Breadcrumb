package eu.kingconquest.sokoban.audio;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.entity.EntityIcon;
import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.sokoban.entities.SokobanEntityIcon;
import eu.kingconquest.sokoban.models.SokobanBoard;

public class SokobanGameAudioObserver implements GameAudioObserver {
    private final Game game;

    public SokobanGameAudioObserver(Game game){
        this.game = game;
    }
    @Override
    public void update() {
        EntityIcon entityIcon = ((SokobanBoard)game.getBoard()).lastEntityIconMoved;
        SokobanAudioManager audioType = null;

        if (entityIcon.equals(SokobanEntityIcon.CRATE))
            audioType = SokobanAudioManager.CRATE_AUDIO;
        else if (entityIcon.equals(SokobanEntityIcon.PLAYER))
            audioType = SokobanAudioManager.PLAYER_AUDIO;

        if (audioType != null)
            audioType.play();
    }
}
