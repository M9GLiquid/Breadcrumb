package framework;

import eu.kingconquest.framework.*;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.entity.EntityType;
import eu.kingconquest.framework.utils.Location;
import org.junit.jupiter.api.Test;

/**
 * A class for integrated unit testing of the GameFramework as a whole, and also used as an example of how to make a new Game with the framework.
 */
public class GameFrameworkTest {

    /**
     * Test the game functionality using a TestGame and a TestEntityType.
     */
    @Test
    public void testGame() {
        TestGame game = new TestGame();
        game.start();
    }

    /**
     * A test entity type for testing purposes, extending EntityType.
     */
    private static class TestEntityType extends EntityType {
        public TestEntityType(String imagePath) {
            super(imagePath);
        }
    }

    /**
     * A test entity class for testing purposes, extending Entity.
     */
    private static class TestEntity extends Entity {
        public TestEntity(int x, int y, TestEntityType entityType) {
            super(new Location(x, y), entityType);
        }
    }

    /**
     * A test game class for testing purposes, extending Game.
     */
    private static class TestGame extends Game {
        @Override
        protected void initiate() {
            TestEntityType testEntityType = new TestEntityType("test.png");
            TestEntity entity = new TestEntity(0, 0, testEntityType);
            getEntities().add(entity);
            board = new GameBoard(30, 20) {
                @Override
                protected boolean isMoveValid(Location location) {
                    // Add custom logic for move validation
                    return true;
                }

                @Override
                public void makeMove(Location direction) {
                    // Add custom logic for making a move
                }
            };
            state = GameState.INITIATING;
            controller = new GameController(this);
            gamePanel = new GamePanel(this);
        }
        @Override
        public void start() {
            // Add custom logic for starting the game
            state = GameState.RUNNING;
        }

        @Override
        protected void reset() {
            // Add custom logic for resetting the game
            state = GameState.RESETTING;
        }

        @Override
        protected void generateLevel() {
            // Add custom logic for generating a new level
        }
    }
}
