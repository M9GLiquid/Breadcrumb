package eu.kingconquest.sokoban.models;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.sokoban.core.Sokoban;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class SokobanBoardTest extends Entity {
    SokobanBoard underTest;
    @Mock
    Sokoban game;

    @BeforeEach
    void setUp() {
        underTest = spy(new SokobanBoard(game, 20, 10));
    }


    @Test
    void test_Constructor(){
        /* Given */

        /* When */

        /* Then */
        assertEquals(underTest.COLS, 10);
        assertEquals(underTest.ROWS, 20);
        assertEquals(underTest.grid.length, 10);
        assertEquals(underTest.grid[0].length, 20);
    }

    @Test
    void isMoveInvalid() {
        /* Given */

        /* When */

        /* Then */

    }

    @Test
    void isCrateAtLocation() {
        /* Given */

        /* When */

        /* Then */

    }

    @Test
    void makeMove() {
        /* Given */

        /* When */

        /* Then */

    }

    @Test
    void updateCrateEntityType() {
        /* Given */

        /* When */

        /* Then */

    }

    @Test
    void isGameOver() {
        /* Given */

        /* When */

        /* Then */

    }

    @Test
    void findCrateAtLocation() {
        /* Given */

        /* When */

        /* Then */

    }

    @Test
    void isLevelComplete() {
        /* Given */

        /* When */

        /* Then */

    }
}