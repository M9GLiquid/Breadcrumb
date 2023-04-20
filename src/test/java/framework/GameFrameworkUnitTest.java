package framework;

import eu.kingconquest.framework.*;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.sokoban.entities.Crate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameFrameworkUnitTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game() {
            @Override
            protected void initiate() {
            }

            @Override
            public void start() {
            }

            @Override
            protected void reset() {
            }

            @Override
            protected void generateLevel() {
            }
        };
    }

    @Test
    public void testIsFinished() {
        game.setState(GameState.RUNNING);
        assertTrue(game.isFinished());

        game.setState(GameState.GAME_OVER);
        assertFalse(game.isFinished());
    }

    @Test
    public void testGameState() {
        game.setState(GameState.RUNNING);
        assertEquals(GameState.RUNNING, game.getState());

        game.setState(GameState.GAME_OVER);
        assertEquals(GameState.GAME_OVER, game.getState());
    }

    @Test
    public void testEntitiesList() {
        assertTrue(game.getEntities().isEmpty());
        Entity entity = new Crate(new Location(0,0));

        game.getEntities().add(entity);
        assertFalse(game.getEntities().isEmpty());
        assertEquals(1, game.getEntities().size());
    }

    @Test
    public void testController() {
        GameController controller = new GameController(game);
        assertEquals(controller, game.getController());
    }

    @Test
    public void testGamePanel() {
        GamePanel gamePanel = new GamePanel(game);
        assertEquals(gamePanel, game.getGamePanel());
    }

    @Test
    public void testFrameDimensions() {
        int expectedWidth = (GameBoard.ROWS * Tile.TILE_SIZE) + 16;
        int expectedHeight = (GameBoard.COLS * Tile.TILE_SIZE) + 39;

        assertEquals(expectedWidth, game.getFrameWidth());
        assertEquals(expectedHeight, game.getFrameHeight());
    }
}
