package eu.kingconquest.sokoban.core;

import eu.kingconquest.framework.Game;
import eu.kingconquest.framework.GameController;
import eu.kingconquest.framework.GameState;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.sokoban.audio.SokobanAudioObserver;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;
import eu.kingconquest.sokoban.io.LevelReader;
import eu.kingconquest.sokoban.ui.GameOverScreen;
import eu.kingconquest.sokoban.ui.StartMenu;

import java.util.List;
import java.util.stream.Collectors;

public class Sokoban extends Game {
    public int level;


    public Sokoban() {
        super("Sokoban");
        state = GameState.INITIATING;

        // Game Controller setup
        new GameController(this);

        // Game View setup
        gameFrame = new GameFrame(this);
        gameFrame.addView(new StartMenu(this), 30, 20);

        // Game Board setup
        board = new SokobanBoard(this, null, null);


        // Game Audio setup
        controller.addAudioObserver(new SokobanAudioObserver());

        // Game Observers Setup
        controller.addObserver(new StateObserver(this));
        controller.notifyObservers();
    }

    @Override
    public void initiate() {
        level = 0;
    }

    @Override
    public void start() {
        generateLevel();
        nextLevel();
    }

    @Override
    protected void generateLevel() {
    }

    public void nextLevel() {
        entities.clear();
        LevelReader.loadLevel("levels.txt", this, level++);
        //getGameFrame().changeSize(getBoard().grid);
        state = GameState.RUNNING;
        gameFrame.revalidate();
        gameFrame.repaint();
        gameFrame.requestFocusInWindow();
    }


    @Override
    public void reset() {
        state = GameState.RESETTING;
    }

    public List<Crate> getCrates() {
        return entities.stream()
                .filter(entity -> entity instanceof Crate)
                .map(entity -> (Crate) entity)
                .collect(Collectors.toList());
    }

    public Player getPlayer() {
        return (Player) entities.stream()
                .filter(entity -> entity instanceof Player)
                .findFirst()
                .orElse(null);
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public void gameOver() {
        new GameOverScreen(this);
    }
}
