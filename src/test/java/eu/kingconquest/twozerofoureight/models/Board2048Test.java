package eu.kingconquest.twozerofoureight.models;

import eu.kingconquest.framework.entity.Entity;
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
    @BeforeEach
    void setUp(){
        Game2048 game = mock(Game2048.class);
        Board2048 board = new Board2048(game, 6, 6);
        underTest = Mockito.spy(board);
        doNothing().when(underTest).addNewTile();
        doNothing().when(underTest).addNewTile();
    }

    @AfterEach
    void tearDown(){
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is right and everything is working.
     */
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

    /**
     * We test isMoveValid in the Board2048.java when direction is left and everything is working.
     */
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

    /**
     * We test isMoveValid in the Board2048.java when direction is up and everything is working.
     */
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

    /**
     * We test isMoveValid in the Board2048.java when direction is down and everything is working.
     */
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



    /**
     * We test isMoveValid in the Board2048.java when direction is right, and you can combine two tiles.
     */
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

    /**
     * We test isMoveValid in the Board2048.java when direction is left, and you can combine two tiles.
     */
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

    /**
     * We test isMoveValid in the Board2048.java when direction is up, and you can combine two tiles.
     */
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

    /**
     * We test isMoveValid in the Board2048.java when direction is down, and you can combine two tiles.
     */
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

    /**
     * X and Y must be either 1 or 0 for the inserted direction in the argument of the method isMoveValid().
     */
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
    void testReverse() {
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
}