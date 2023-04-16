package eu.kingconquest.Core;

import eu.kingconquest.Utils.Location;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameController extends KeyAdapter implements GameStrategy{
    private final Sokoban game;
    private final List<GameObserver> observers;

    public GameController(Sokoban game) {
        this.game = game;
        observers = new ArrayList<>();
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer: observers)
            observer.update(game);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        Location location = new Location(0, 0);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> location.setY(-1);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> location.setY(1);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> location.setX(-1);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> location.setX(1);
        }

        if (location.getX() != 0 || location.getY() != 0)
            game.getBoard().makeMove(location);
    }
}
