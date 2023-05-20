package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.observers.GameStateObserver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GuiControllerTest {
    private GuiController underTest;
    @Mock
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        underTest = spy(new GuiController(gameBoard));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void constructorTest(){
        assertNotNull(underTest);
        assertEquals(gameBoard, underTest.gameBoard);
        assertTrue(underTest.stateObservers.isEmpty());
        assertTrue(underTest.viewObservers.isEmpty());
        assertTrue(underTest.audioObservers.isEmpty());
    }

    @Test
    void test_keyPressed_escape(){
        /* Given */
         KeyEvent event = mock(KeyEvent.class);

         /* Skip */
        doNothing().when(underTest).notifyStateObservers();

        /* When */
        doReturn(KeyEvent.VK_ESCAPE).when(event).getKeyCode();
        underTest.keyPressed(event);

        /* Then */
        verify(gameBoard, times(1)).setState(any(GameState.class));

    }

    @Test
    void test_State_Observer(){
        /* Given */
        GameStateObserver stateobserver1 = mock(GameStateObserver.class);
        GameStateObserver stateobserver2 = mock(GameStateObserver.class);
        underTest.stateObservers.add(stateobserver1);


        /*When */
        underTest.addStateObserver(stateobserver2);


        /* Then */
        verify(underTest.stateObservers).add(any(GameStateObserver.class));

    }

}