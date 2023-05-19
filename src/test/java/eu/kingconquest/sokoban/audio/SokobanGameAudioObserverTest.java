package eu.kingconquest.sokoban.audio;

import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.entities.SokobanEntityIcon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SokobanGameAudioObserverTest {

    SokobanGameAudioObserver underTest;
    SokobanAudioManager crateAudio = mock(SokobanAudioManager.class);
    SokobanAudioManager playerAudio = mock(SokobanAudioManager.class);

    @BeforeEach
    void setUp() {
        underTest = spy(new SokobanGameAudioObserver(
                crateAudio,
                playerAudio)
        );
    }

    @Test
    void test_update_EntityIsCrate() {
        // Given
        Crate crate = mock(Crate.class);

        // When
        doReturn(SokobanEntityIcon.CRATE).when(crate).getEntityType();
        underTest.update(crate);

        // Then
        verify(crateAudio).play();

    }

    @Test
    void test_update_EntityIsPlayer() {
        // Given
        Player player = mock(Player.class);

        // When
        doReturn(SokobanEntityIcon.PLAYER).when(player).getEntityType();
        underTest.update(player);

        // Then
        verify(playerAudio).play();

    }
}