package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.framework.observers.GameStateObserver;
import eu.kingconquest.framework.observers.GameViewObserver;
import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class KeyBoardControllerTest {
    private KeyBoardController underTest;
    private final Set<GameStateObserver> stateObserversTest = new HashSet<>();
    private final Set<GameViewObserver> viewObserversTest = new HashSet<>();
    private final Set<GameAudioObserver> audioObserversTest = new HashSet<>();
    private Location direction;

    @BeforeEach
    void setUp() {
        GameBoard gameBoard = mock(GameBoard.class);
        underTest = spy(new KeyBoardController(gameBoard));
        direction = mock(Location.class);
        direction.setY(0);
        direction.setX(0);


    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void constructorTest(){
        assertNotNull(underTest);
        assertNotNull(stateObserversTest);
        assertNotNull(viewObserversTest);
        assertNotNull(audioObserversTest);
    }
    @Test
    void keyPressed_UP() {
        /* Given */
        KeyEvent event = mock(KeyEvent.class);
        /* Skip */
        doNothing().when(underTest).notifyStateObservers();

        /* When */
        doReturn(KeyEvent.VK_UP).when(event).getKeyCode();
        underTest.keyPressed(event);

        /* Then */
        verify(direction).setY(-1);
        verify(direction.getY()).equals(-1);

    }

    @Test
    void actionPerformed() {
    }
}