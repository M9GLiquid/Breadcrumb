package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.controllers.GuiController;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.io.DataWriter;
import eu.kingconquest.framework.io.GameData;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.observers.GameAudioObserver;
import eu.kingconquest.framework.observers.GameStateObserver;
import eu.kingconquest.framework.observers.GameViewObserver;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.framework.ui.Notification;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.views.FloatingButtonsView;
import eu.kingconquest.framework.views.GameGuiView;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.entities.SokobanEntityType;
import eu.kingconquest.sokoban.io.LevelReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SokobanTest {

    @Mock
    private GameFrame gameFrame;

    private Sokoban underTest;
    private GameBoard board;

    @BeforeEach
    void setUp() {
        gameFrame = mock(GameFrame.class);
        underTest = spy(new Sokoban(gameFrame));
        board = mock(GameBoard.class);
    }

    @AfterEach
    void tearDown() {
        reset(gameFrame);
        reset(underTest);
        reset(board);
    }

    @Test
    void test_Initiate() {
        /* Given */
        GameStrategy controller = mock(GameStrategy.class);

        /* When */
        // Skip this step
        doNothing().when(underTest).setDesiredController(any(GameStrategy.class));
        // Do it mock way instead
        doReturn(controller).when(underTest).getController();

        underTest.initiate();

        /* Then */
        verify(controller).addAudioObserver(any(GameAudioObserver.class));
        verify(controller).addStateObserver(any(GameStateObserver.class));
        verify(controller).addViewObserver(any(GameViewObserver.class));

    }

    @Test
    void test_Start_NextLevel_ReturnTrue() {
        /* Given */

        /* When */
        doReturn(true).when(underTest).nextLevel();

        underTest.start();
        /* Then */
        verify(underTest, never()).getBoard();
    }

    @Test
    void test_Start_NextLevel_ReturnFalse_ControllerNotInstanceOfGUIController() {
        /* Given */
        GuiController guiController = mock(GuiController.class);

        /* When */
        doReturn(false).when(underTest).nextLevel();
        doReturn(board).when(underTest).getBoard();
        doReturn(guiController).when(underTest).getController();

        /* Then */
        // Mock Constructor call "try-with-resources"
        try (MockedConstruction<FloatingButtonsView> mocked = mockConstruction(FloatingButtonsView.class)) {
            underTest.start();
            verify(board, times(1)).setState(any(GameState.class));

            // check to see if the FloatingButtonsView instance was created.
            assertEquals(1, mocked.constructed().size());
        }
    }

    @Test
    void test_NextLevel_GameState_is_LEVEL_COMPLETE_entitiesCleared_loadLevelReturns_0() {
        /* Given */
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(new Crate(new Location(0, 0)));

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(entities).when(board).getEntities();
        doReturn(GameState.LEVEL_COMPLETE).when(board).getState();
        doNothing().when(underTest).setGameView();

        /* Then */
        try (MockedStatic<LevelReader> mocked = mockStatic(LevelReader.class)) {
            mocked.when(() -> LevelReader.loadLevel(anyString(), any(GameBoard.class), anyInt())).thenReturn(0);
            underTest.nextLevel();
        }

        // entities list should be cleared
        assertEquals(0, entities.size());
    }

    @Test
    void test_NextLevel_GameState_is_INITIATING_entitiesCleared_loadLevelReturns_1() {
        /* Given */
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(new Crate(new Location(0, 0)));

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(entities).when(board).getEntities();
        doReturn(GameState.INITIATING).when(board).getState();
        doNothing().when(underTest).gameOver();

        /* Then */
        try (MockedStatic<LevelReader> mocked = mockStatic(LevelReader.class)) {
            mocked.when(() -> LevelReader.loadLevel(anyString(), any(GameBoard.class), anyInt())).thenReturn(1);
            underTest.nextLevel();
        }

        // entities list should be cleared
        assertEquals(0, entities.size());
        verify(underTest).gameOver();
    }

    @Test
    void test_NextLevel_GameState_is_RESETTING_entitiesCleared_loadLevelReturns_0() {
        /* Given */
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(new Crate(new Location(0, 0)));

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(entities).when(board).getEntities();
        doReturn(GameState.RESETTING).when(board).getState();
        doNothing().when(underTest).setGameView();

        /* Then */
        try (MockedStatic<LevelReader> mocked = mockStatic(LevelReader.class)) {
            underTest.nextLevel();
            mocked.when(() -> LevelReader.loadLevel(anyString(), any(GameBoard.class), anyInt())).thenReturn(0);
        }

        // entities list should be cleared
        assertEquals(0, entities.size());
    }

    @Test
    void test_SetGameView() {
        /* Given */
        GameFrame frame = mock(GameFrame.class);
        GameGuiView view = mock(GameGuiView.class);

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(frame).when(underTest).getGameFrame();
        doReturn(view).when(frame).getGameGuiView();

        /* Then */
        underTest.setGameView();

        verify(frame).addView(any(GameGuiView.class), anyInt(), anyInt());
    }

    @Test
    void test_Restart() {
        /* Given */
        underTest.level = 0;

        /* When */
        doReturn(board).when(underTest).getBoard();
        doNothing().when(underTest).start();

        /* Then */
        underTest.restart();
        assertEquals(-1, underTest.level);

        verify(underTest).start();
    }

    @Test
    void test_Save() {
        /* Given */

        /* When */
        doNothing().when(underTest).setGameData(any(GameData.class));
        doReturn(board).when(underTest).getBoard();
        doReturn(new ArrayList<>()).when(board).getEntities();

        /* Then */
        try (MockedStatic<DataWriter> mockedDataWriter = mockStatic(DataWriter.class)) {
            mockedDataWriter.when(() -> DataWriter.save(any(Game.class))).thenReturn("");
            try (MockedStatic<Notification> mockedNotification = mockStatic(Notification.class)) {
                underTest.save();
                mockedNotification.verify(() -> Notification.showNotification(any(Game.class), anyString()));
            }
        }
    }

    @Test
    void test_Load_GameHasLoadedSuccessfully() {
        /* Given */

        /* When */
        doNothing().when(underTest).setData();
        doNothing().when(underTest).setGameView();
        doReturn(board).when(underTest).getBoard();

        try (MockedStatic<DataReader> mockedDataReader = mockStatic(DataReader.class)) {
            mockedDataReader.when(() -> DataReader.load(any(Game.class))).thenReturn("Game loaded successfully!");
            try (MockedStatic<Notification> mockedNotification = mockStatic(Notification.class)) {
                underTest.load();
                // After the method execution, verify the calls
                mockedNotification.verify(() -> Notification.showNotification(any(Game.class), anyString()));
            }
        }

        /* Then */
        verify(underTest).setData();
        // TODO: Verify after async time, how does this work?
//        verify(underTest).setGameView();
    }

    @Test
    void test_SetGameData() {
        /* Given */
        GameData data = mock(GameData.class);
        data.level = 1;
        Tile tile = mock(Tile.class);
        data.grid = new Tile[][]{{tile, tile}, {tile, tile}};
        data.entities = new ArrayList<>();

        /* When */
        doReturn(data).when(underTest).getGameData();
        doReturn(board).when(underTest).getBoard();

        underTest.setData();

        /* Then */
        verify(board).setEntities(data.entities);
        assertEquals(data.level, underTest.level);
        assertEquals(data.grid, board.grid);
        assertEquals(data.grid[0].length, board.COLS);
        assertEquals(data.grid.length, board.ROWS);
    }

    @Test
    void test_GetCrates() {
        /* Given */
        Entity entity1 = mock(Crate.class);
        Entity entity2 = mock(Crate.class);
        Entity entity3 = mock(Player.class); // Not a crate

        List<Entity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);
        entities.add(entity3);

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(entities).when(board).getEntities();
        List<Crate> crates = underTest.getCrates();

        /* Then */
        assertEquals(2, crates.size()); // Only two entities are of type Crate
    }

    @Test
    void test_GetPlayer() {
        /* Given */
        Entity entity1 = mock(Crate.class);
        Entity entity2 = mock(Crate.class);
        Entity entity3 = mock(Player.class); // Not a crate

        List<Entity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);
        entities.add(entity3);

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(entities).when(board).getEntities();
        Player player = underTest.getPlayer();

        /* Then */
        assertNotNull(player); // Make sure a player was returned
    }

    @Test
    void test_GetGoals() {
        /* Given */
        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);
        Tile tile3 = mock(Tile.class); // Not a crate

        List<Entity> entities = new ArrayList<>();
        entities.add(tile1);
        entities.add(tile2);
        entities.add(tile3);

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(entities).when(board).getEntities();

        doReturn(SokobanEntityType.GROUND_MARKED).when(tile1).getEntityType();
        doReturn(SokobanEntityType.GROUND_MARKED).when(tile2).getEntityType();
        doReturn(SokobanEntityType.GROUND).when(tile3).getEntityType();
        List<Tile> goals = underTest.getGoals();

        /* Then */
        assertEquals(2, goals.size()); // Only two entities are of type Crate
    }

    @Test
    void test_Pause() {
        /* Given */
        GameFrame frame = mock(GameFrame.class);

        /* When */
        doReturn(frame).when(underTest).getGameFrame();
        underTest.pause();

        /* Then */
        verify(frame).addView(any(JPanel.class), anyInt(), anyInt());
    }

    @Test
    void test_GameOver_State_GameOver() {
        /* Given */
        GameFrame frame = mock(GameFrame.class);

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(GameState.GAME_OVER).when(board).getState();

        doReturn(frame).when(underTest).getGameFrame();
        underTest.gameOver();

        /* Then */
        verify(frame).addView(any(Menu.class), anyInt(), anyInt());
    }

    @Test
    void test_GameOver_State_Win() {
        /* Given */
        GameFrame frame = mock(GameFrame.class);

        /* When */
        doReturn(board).when(underTest).getBoard();
        doReturn(GameState.WIN).when(board).getState();

        doReturn(frame).when(underTest).getGameFrame();
        underTest.gameOver();

        /* Then */
        verify(frame).addView(any(Menu.class), anyInt(), anyInt());
    }

}
