package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.observers.GameObserver;
import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KeyBoardControllerTest {
    private KeyBoardController underTest;
    private final Set<GameObserver> stateObserversTest = new HashSet<>();
    private final Set<GameObserver> viewObserversTest = new HashSet<>();
    private final Set<GameObserver> audioObserversTest = new HashSet<>();
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = mock(GameBoard.class);
        underTest = spy(new KeyBoardController(gameBoard));


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


        /* When */
        doNothing().when(underTest).notifyStateObservers();
        doNothing().when(gameBoard).makeMove(any(Location.class));
        doNothing().when(underTest).notifyViewObservers();

        doReturn(KeyEvent.VK_UP).when(event).getKeyCode();
        doReturn(GameState.RUNNING).when(gameBoard).getState();
        underTest.keyPressed(event);

        /* Then */
        verify(underTest).notifyStateObservers();
        verify(underTest).notifyStateObservers();
        verify(gameBoard).makeMove(any(Location.class));

    }
    @Test
    void keyPressed_DOWN() {
        /* Given */
        KeyEvent event = mock(KeyEvent.class);


        /* When */
        doNothing().when(underTest).notifyStateObservers();
        doNothing().when(gameBoard).makeMove(any(Location.class));
        doNothing().when(underTest).notifyViewObservers();

        doReturn(KeyEvent.VK_DOWN).when(event).getKeyCode();
        doReturn(GameState.RUNNING).when(gameBoard).getState();
        underTest.keyPressed(event);

        /* Then */
        verify(underTest).notifyStateObservers();
        verify(underTest).notifyStateObservers();
        verify(gameBoard).makeMove(any(Location.class));

    }
    @Test
    void keyPressed_LEFT() {
        /* Given */
        KeyEvent event = mock(KeyEvent.class);


        /* When */
        doNothing().when(underTest).notifyStateObservers();
        doNothing().when(gameBoard).makeMove(any(Location.class));
        doNothing().when(underTest).notifyViewObservers();

        doReturn(KeyEvent.VK_LEFT).when(event).getKeyCode();
        doReturn(GameState.RUNNING).when(gameBoard).getState();
        underTest.keyPressed(event);

        /* Then */
        verify(underTest).notifyStateObservers();
        verify(underTest).notifyStateObservers();
        verify(gameBoard).makeMove(any(Location.class));

    }
    @Test
    void keyPressed_RIGHT() {
        /* Given */
        KeyEvent event = mock(KeyEvent.class);


        /* When */
        doNothing().when(underTest).notifyStateObservers();
        doNothing().when(gameBoard).makeMove(any(Location.class));
        doNothing().when(underTest).notifyViewObservers();

        doReturn(KeyEvent.VK_RIGHT).when(event).getKeyCode();
        doReturn(GameState.RUNNING).when(gameBoard).getState();
        underTest.keyPressed(event);

        /* Then */
        verify(underTest).notifyStateObservers();
        verify(underTest).notifyStateObservers();
        verify(gameBoard).makeMove(any(Location.class));

    }
    @Test
    void keyPressed_ESC() {
        /* Given */
        KeyEvent event = mock(KeyEvent.class);


        /* When */
        doNothing().when(underTest).notifyStateObservers();
        doNothing().when(gameBoard).setState(any(GameState.class));
        doNothing().when(underTest).notifyViewObservers();

        doReturn(KeyEvent.VK_ESCAPE).when(event).getKeyCode();
        doReturn(GameState.RUNNING).when(gameBoard).getState();
        underTest.keyPressed(event);

        /* Then */
        verify(underTest).notifyStateObservers();
        verify(underTest).notifyStateObservers();
        verify(gameBoard).setState(GameState.PAUSED);

    }

}