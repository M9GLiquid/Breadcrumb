package eu.kingconquest.twozerofoureight.models;

import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.twozerofoureight.core.EntityIcon2048;
import eu.kingconquest.twozerofoureight.core.Game2048;
import eu.kingconquest.twozerofoureight.entities.Block;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Board2048Test {
    Board2048 underTest;
    Game2048 game;
    @BeforeEach
    void setUp(){
        game = mock(Game2048.class);
        Board2048 board = new Board2048(game, 6, 6);
        underTest = Mockito.spy(board);
        doNothing().when(underTest).addNewTile();
        doNothing().when(underTest).addNewTile();
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void test_isMoveValidRight() {
        // Given
        Entity block = new Block(new Location(4,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(4,1), new Location(4,1));

        // Then
        assertThat(underTest.isMoveValid(block, new Location(1,0))).isEqualTo(false);
    }

    @Test
    void test_isMoveValidLeft() {
        // Given
        Entity block = new Block(new Location(1,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block, new Location(-1,0))).isEqualTo(false);
    }

    @Test
    void test_isMoveValidUp() {
        // Given
        Entity block = new Block(new Location(1,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block, new Location(0,-1))).isEqualTo(false);
    }

    @Test
    void test_isMoveValidDown() {
        // Given
        Entity block = new Block(new Location(1,4), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block, new Location(0,1))).isEqualTo(false);
    }

    @Test
    void test_isCombineValidRight() {
        // Given
        Entity block1 = new Block(new Location(3,1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(4,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block1, new Location(1,0))).isEqualTo(true);
    }

    @Test
    void test_isCombineValidLeft() {
        // Given
        Entity block1 = new Block(new Location(1,1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(2,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block2, new Location(-1,0))).isEqualTo(true);
    }

    @Test
    void test_isCombineValidUp() {
        // Given
        Entity block1 = new Block(new Location(4,1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(4,2), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block2, new Location(0,-1))).isEqualTo(true);
    }

    @Test
    void test_isCombineValidDown() {
        // Given
        Entity block1 = new Block(new Location(4,3), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(4,4), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block1, new Location(0,1))).isEqualTo(true);
    }

    @Test
    void test_ifDirectionIsInvalid() {
        // Given
        Entity block = new Block(new Location(4,3), EntityIcon2048.E2);


        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(true).when(underTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(underTest.isMoveValid(block, new Location(3,2))).isEqualTo(false);
    }

    @Test
    void test_getScore() {
        // Given
        underTest.score = 10;

        // When

        // Then
        assertEquals(10, underTest.getScore());

    }

    @Test
    void test_resetScore() {
        // Given
        underTest.score = 10;

        // When
        underTest.resetScore();

        // Then
        assertEquals(0, underTest.score);
    }

    @Test
    void test_isMatch() {
        // Given
        Location location = mock(Location.class);
        Entity block1 = new Block(location, EntityIcon2048.E2);
        Entity block2 = new Block(location, EntityIcon2048.E2);

        // When
        boolean underTest = this.underTest.isMatch(block1, block2);

        // Then
        assertTrue(underTest);
    }

    @Test
    void test_entityExist() {
        // Given
        underTest.getEntities().clear();
        Location location = mock(Location.class);
        Entity block1 = new Block(location, EntityIcon2048.E2);
        //Entity block2 = new Block(location, EntityIcon2048.E2);
        underTest.getEntities().add(block1);

        // When
        boolean entityExist1 = underTest.entityExist(block1);
        boolean entityExist2 = underTest.entityExist(null);

        // Then
        assertTrue(entityExist1);
        assertFalse(entityExist2);
    }

    @Test
    void test_isGameOver() {
        //Given

        //When
        doReturn(true).when(underTest).isFull();
        doReturn(false).when(underTest).isAnyMovePossible();

        //Then
        assertTrue(underTest.isGameOver());

    }

    @Test
    void test_isWin() {
        //Given
        ArrayList<Entity> entities = new ArrayList<>();
        Location location = mock(Location.class);
        Entity block1 = new Block(location, EntityIcon2048.E64);
        entities.add(block1);

        //When
        doReturn(entities).when(underTest).getEntities();
        doReturn(underTest).when(underTest.game).getBoard();

        //Then
        underTest.isWin();
        assertTrue(underTest.game.hasWon);

    }

    @Test
    void test_reverse() {
        //Given
        Entity block1 = new Block(new Location(1, 1), EntityIcon2048.WALL);
        Entity block2 = new Block(new Location(2, 2), EntityIcon2048.WALL);
        ArrayList<Entity> initialEntities = new ArrayList<>();
        initialEntities.add(block1);
        initialEntities.add(block2);

        // Prepare the expected list of entities after reverse
        ArrayList<Entity> expectedEntities = new ArrayList<>();
        Entity block3 = new Block(new Location(1, 4), EntityIcon2048.WALL);
        Entity block4 = new Block(new Location(2, 3), EntityIcon2048.WALL);
        expectedEntities.add(block3);
        expectedEntities.add(block4);

        //When
        // Stub the dependencies
        doAnswer(invocation -> {
            Block block = invocation.getArgument(0);
            return initialEntities.stream().anyMatch(e -> e.getLocation().equals(block.getLocation()));
        }).when(underTest).entityExist(any());

        doAnswer(invocation -> {
            Location location = invocation.getArgument(0);
            return initialEntities.stream().filter(e -> e.getLocation().equals(location)).findFirst().orElse(null);
        }).when(underTest).getEntity(any());

        // Call the method under test
        underTest.reverse();

        ArgumentCaptor<ArrayList<Entity>> captor = ArgumentCaptor.forClass(ArrayList.class);
        verify(underTest).setEntities(captor.capture());
        ArrayList<Entity> reversedEntities = captor.getValue();

        // Compare the reversed entities with expected entities
        assertEquals(expectedEntities.size(), reversedEntities.size());

        for (int i = 0; i < expectedEntities.size(); i++) {
            assertEquals(expectedEntities.get(i).getLocation().getX(), reversedEntities.get(i).getLocation().getX());
            assertEquals(expectedEntities.get(i).getLocation().getY(), reversedEntities.get(i).getLocation().getY());
        }
    }
    @Test
    void test_productEntity() {
        assertEquals(EntityIcon2048.E4, underTest.productEntity(EntityIcon2048.E2));
        assertEquals(EntityIcon2048.E8, underTest.productEntity(EntityIcon2048.E4));
        assertEquals(EntityIcon2048.E16, underTest.productEntity(EntityIcon2048.E8));
        assertEquals(EntityIcon2048.E32, underTest.productEntity(EntityIcon2048.E16));
        assertEquals(EntityIcon2048.E64, underTest.productEntity(EntityIcon2048.E32));
        assertEquals(EntityIcon2048.E128, underTest.productEntity(EntityIcon2048.E64));
        assertEquals(EntityIcon2048.E256, underTest.productEntity(EntityIcon2048.E128));
        assertEquals(EntityIcon2048.E512, underTest.productEntity(EntityIcon2048.E256));
        assertEquals(EntityIcon2048.E1024, underTest.productEntity(EntityIcon2048.E512));
        assertEquals(EntityIcon2048.E2048, underTest.productEntity(EntityIcon2048.E1024));
        assertEquals(EntityIcon2048.E4096, underTest.productEntity(EntityIcon2048.E2048));
        assertEquals(EntityIcon2048.E8192, underTest.productEntity(EntityIcon2048.E4096));
        assertEquals(EntityIcon2048.E16384, underTest.productEntity(EntityIcon2048.E8192));
        assertNull(underTest.productEntity(EntityIcon2048.WALL));
    }

    @Test
    void test_addScore() {
        //Given

        //When
        underTest.addScore(EntityIcon2048.E2);
        underTest.addScore(EntityIcon2048.E4);
        underTest.addScore(EntityIcon2048.E8);
        underTest.addScore(EntityIcon2048.E16);
        underTest.addScore(EntityIcon2048.E32);
        underTest.addScore(EntityIcon2048.E64);
        underTest.addScore(EntityIcon2048.E128);
        underTest.addScore(EntityIcon2048.E256);
        underTest.addScore(EntityIcon2048.E512);
        underTest.addScore(EntityIcon2048.E1024);
        underTest.addScore(EntityIcon2048.E2048);
        underTest.addScore(EntityIcon2048.E4096);
        underTest.addScore(EntityIcon2048.E8192);
        underTest.addScore(EntityIcon2048.E16384);

        //Then
        assertEquals(32764, underTest.score);
    }

    @Test
    void test_compress() {
        Entity block1 = new Block(new Location(1, 1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(2, 1), EntityIcon2048.E2);
        Entity block3 = new Block(new Location(3, 1), EntityIcon2048.E4);
        Entity block4 = new Block(new Location(4, 1), EntityIcon2048.E4);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);
        entities.add(block3);
        entities.add(block4);

        // When
        doReturn(entities).when(underTest).getEntities();

        // Call the method under test
        underTest.compress();

        // Then
        assertEquals(4, entities.size());
        assertEquals(new Location(1, 1), entities.get(0).getLocation());
        assertEquals(EntityIcon2048.E2, entities.get(0).getEntityType());
        assertEquals(new Location(2, 1), entities.get(1).getLocation());
        assertEquals(EntityIcon2048.E2, entities.get(1).getEntityType());
    }

    @Test
    void test_isAnyMovePossible() {
        // Given
        Entity block1 = new Block(new Location(1, 1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(1, 2), EntityIcon2048.E2);
        Entity block3 = new Block(new Location(1, 3), EntityIcon2048.E8);
        Entity block4 = new Block(new Location(1, 4), EntityIcon2048.E16);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);
        entities.add(block3);
        entities.add(block4);

        underTest.setEntities(entities);

        doReturn(entities).when(underTest).getEntities();

        // When
        boolean result = underTest.isAnyMovePossible();

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void test_makeMove_down() {
        // Given
        Location down = new Location(0, 1);

        GameStrategy controller = mock(GameStrategy.class);
        doReturn(controller).when(game).getController();
        doNothing().when(controller).notifyAudioObservers();
        doReturn(false).when(underTest).isGameOver();

        doNothing().when(underTest).compress();
        doNothing().when(underTest).merge();
        doNothing().when(underTest).reverse();

        // When
        underTest.makeMove(down);

        // Then
        verify(underTest, times(2)).reverse();
        verify(underTest).merge();
        verify(underTest, times(2)).compress();
        verify(underTest).addNewTile();
        verify(controller).notifyAudioObservers();
    }
    @Test
    void test_makeMove_up() {
        // Given
        Location up = new Location(0, -1);

        GameStrategy controller = mock(GameStrategy.class);
        doReturn(controller).when(game).getController();
        doNothing().when(controller).notifyAudioObservers();
        doReturn(false).when(underTest).isGameOver();

        doNothing().when(underTest).compress();
        doNothing().when(underTest).merge();

        // When
        underTest.makeMove(up);

        // Then
        verify(underTest).merge();
        verify(underTest, times(2)).compress();
        verify(underTest).addNewTile();
        verify(controller).notifyAudioObservers();
    }

    @Test
    void test_makeMove_right() {
        // Given
        Location right = new Location(1, 0);

        GameStrategy controller = mock(GameStrategy.class);
        doReturn(controller).when(game).getController();
        doNothing().when(controller).notifyAudioObservers();
        doReturn(false).when(underTest).isGameOver();

        doNothing().when(underTest).transpose();
        doNothing().when(underTest).compress();
        doNothing().when(underTest).merge();
        doNothing().when(underTest).reverse();

        // When
        underTest.makeMove(right);

        // Then
        verify(underTest, times(2)).transpose();
        verify(underTest, times(2)).reverse();
        verify(underTest).merge();
        verify(underTest, times(2)).compress();
        verify(underTest).addNewTile();
        verify(controller).notifyAudioObservers();
    }

    @Test
    void test_makeMove_left() {
        // Given
        Location left = new Location(-1, 0);

        GameStrategy controller = mock(GameStrategy.class);
        doReturn(controller).when(game).getController();
        doNothing().when(controller).notifyAudioObservers();
        doReturn(false).when(underTest).isGameOver();

        doNothing().when(underTest).transpose();
        doNothing().when(underTest).compress();
        doNothing().when(underTest).merge();

        // When
        underTest.makeMove(left);

        // Then
        verify(underTest, times(2)).transpose();
        verify(underTest).merge();
        verify(underTest, times(2)).compress();
        verify(underTest).addNewTile();
        verify(controller).notifyAudioObservers();
    }

    @Test
    void test_makeMove_gameOver(){
        // Given
        Location left = new Location(-1, 0);

        GameStrategy controller = mock(GameStrategy.class);
        GameBoard board = mock(GameBoard.class);
        doReturn(board).when(game).getBoard();
        doReturn(controller).when(game).getController();
        doNothing().when(controller).notifyAudioObservers();
        doReturn(true).when(underTest).isGameOver();

        // When
        underTest.makeMove(left);

        // Then
        verify(board).setState(GameState.GAME_OVER);
    }

    @Test
    void test_merge() {
        // Given
        Entity block1 = new Block(new Location(1, 1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(1, 2), EntityIcon2048.E2);
        Entity block3 = new Block(new Location(1, 3), EntityIcon2048.E4);
        Entity block4 = new Block(new Location(1, 4), EntityIcon2048.E8);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);
        entities.add(block3);
        entities.add(block4);

        ArrayList<Entity> mergedEntities = new ArrayList<>();
        mergedEntities.add(new Block(new Location(1, 1), EntityIcon2048.E4));
        mergedEntities.add(new Block(new Location(1, 3), EntityIcon2048.E8));

        // When
        doReturn(entities).when(underTest).getEntities();
        doReturn(false).when(underTest).entityExist(any(Entity.class));
        doReturn(true).when(underTest).isMatch(any(Entity.class), any(Entity.class ));
        underTest.merge();

        // Then
        assertEquals(EntityIcon2048.E4, mergedEntities.get(0).getEntityType());
        assertEquals(EntityIcon2048.E8, mergedEntities.get(1).getEntityType());
    }

    @Test
    void test_transpose() {
        // Given
        Entity block1 = new Block(new Location(1, 1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(2, 2), EntityIcon2048.E4);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);
        //doReturn(entities).when(underTest).getEntities();
        underTest.setEntities(entities);

        // When
        underTest.transpose();

        // Then
        assertThat(underTest.getEntities().get(0).getLocation()).isEqualTo(new Location(1,4));
        assertThat(underTest.getEntities().get(1).getLocation()).isEqualTo(new Location(2,3));
    }
}