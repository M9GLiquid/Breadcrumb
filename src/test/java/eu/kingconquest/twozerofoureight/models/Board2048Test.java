package eu.kingconquest.twozerofoureight.models;

import eu.kingconquest.framework.entities.Entity;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.twozerofoureight.core.EntityIcon2048;
import eu.kingconquest.twozerofoureight.core.Game2048;
import eu.kingconquest.twozerofoureight.entities.Block;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class Board2048Test {
    Board2048 toTest;
    @BeforeEach
    void setUp(){
        Game2048 game = Mockito.mock(Game2048.class);
        Board2048 board = new Board2048(game, 6, 6);
        toTest = Mockito.spy(board);
    }

    @AfterEach
    void tearDown(){
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is right and everything is working.
     */
    @Test
    void isMoveValidRight() {
        // Given
        Entity block = new Block(new Location(4,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(4,1), new Location(4,1));

        // Then
        assertThat(toTest.isMoveValid(block, new Location(1,0))).isEqualTo(false);
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is left and everything is working.
     */
    @Test
    void isMoveValidLeft() {
        // Given
        Entity block = new Block(new Location(1,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block, new Location(-1,0))).isEqualTo(false);
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is up and everything is working.
     */
    @Test
    void isMoveValidUp() {
        // Given
        Entity block = new Block(new Location(1,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block, new Location(0,-1))).isEqualTo(false);
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is down and everything is working.
     */
    @Test
    void isMoveValidDown() {
        // Given
        Entity block = new Block(new Location(1,4), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block, new Location(0,1))).isEqualTo(false);
    }



    /**
     * We test isMoveValid in the Board2048.java when direction is right, and you can combine two tiles.
     */
    @Test
    void isCombineValidRight() {
        // Given
        Entity block1 = new Block(new Location(3,1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(4,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block1, new Location(1,0))).isEqualTo(true);
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is left, and you can combine two tiles.
     */
    @Test
    void isCombineValidLeft() {
        // Given
        Entity block1 = new Block(new Location(1,1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(2,1), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block2, new Location(-1,0))).isEqualTo(true);
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is up, and you can combine two tiles.
     */
    @Test
    void isCombineValidUp() {
        // Given
        Entity block1 = new Block(new Location(4,1), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(4,2), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block2, new Location(0,-1))).isEqualTo(true);
    }

    /**
     * We test isMoveValid in the Board2048.java when direction is down, and you can combine two tiles.
     */
    @Test
    void isCombineValidDown() {
        // Given
        Entity block1 = new Block(new Location(4,3), EntityIcon2048.E2);
        Entity block2 = new Block(new Location(4,4), EntityIcon2048.E2);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block1);
        entities.add(block2);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block1, new Location(0,1))).isEqualTo(true);
    }

    /**
     * X and Y must be either 1 or 0 for the inserted direction in the argument of the method isMoveValid().
     */
    @Test
    void ifDirectionIsInvalid() {
        // Given
        Entity block = new Block(new Location(4,3), EntityIcon2048.E2);


        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(block);

        // When
        Mockito.doReturn(entities).when(toTest).getEntities();
        Mockito.doReturn(true).when(toTest).isLocationMatch(new Location(1,1), new Location(1,1));

        // Then
        assertThat(toTest.isMoveValid(block, new Location(3,2))).isEqualTo(false);
    }
}