package eu.kingconquest.framework;

/**
 * Represents the various game states for a game implementation. The game states
 * provide information about the current status of the game, such as whether it is running,
 * has been won, is resetting, etc.
 */
public enum GameState {
    INITIATING,
    WIN,
    GAME_OVER,
    LEVEL_COMPLETE,
    RESETTING,
    PAUSED,
    RUNNING
}
