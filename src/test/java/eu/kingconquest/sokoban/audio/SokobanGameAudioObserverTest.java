package eu.kingconquest.sokoban.audio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;

class SokobanGameAudioObserverTest {

    SokobanGameAudioObserver underTest;

    @BeforeEach
    void setUp() {
        underTest = spy();
    }

    @Test
    void test_update_EntityIsCrate() {

    }

    @Test
    void test_update_EntityIsPlayer() {
    }
}