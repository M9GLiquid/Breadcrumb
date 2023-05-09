package eu.kingconquest.framework.controllers;

import eu.kingconquest.framework.observers.AudioObserver;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.observers.GameStateObserver;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.strategies.GameStrategy;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.observers.GameViewObserver;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GuiController implements GameStrategy {
    private final GameBoard gameBoard;
    private final List<GameStateObserver> stateObservers = new ArrayList<>();
    private final List<GameViewObserver> viewObservers = new ArrayList<>();
    private final List<AudioObserver> audioObservers = new ArrayList<>();

    public GuiController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void addStateObserver(GameStateObserver observer) {
        stateObservers.add(observer);
    }

    @Override
    public void addViewObserver(GameViewObserver observer) {
        viewObservers.add(observer);
    }

    @Override
    public void addAudioObserver(AudioObserver observer) {
        audioObservers.add(observer);
    }

    @Override
    public void notifyStateObservers() {
        for (GameStateObserver observer : stateObservers)
            observer.update();
    }

    @Override
    public void notifyViewObservers() {
        for (GameViewObserver observer : viewObservers)
            observer.update();
    }

    @Override
    public void notifyAudioObservers(Entity entity) {
        for (AudioObserver observer : audioObservers)
            observer.update(entity);
    }

    @Override
    public void removeStateObservers() {
        stateObservers.clear();
    }

    @Override
    public void removeAudioObservers() {
        audioObservers.clear();
    }

    @Override
    public void removeGameViewObservers() {
        viewObservers.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Location direction = new Location(0, 0);

        // Move the direction of the button pressed
        String command = e.getActionCommand();

        switch (command) {
            case "UP" -> direction.setY(-1);
            case "RIGHT" -> direction.setX(1);
            case "DOWN" -> direction.setY(1);
            case "LEFT" -> direction.setX(-1);
        }

        if (direction.getX() != 0 || direction.getY() != 0 && gameBoard.getState().equals(GameState.RUNNING))
            gameBoard.makeMove(direction);

        notifyStateObservers();
        notifyViewObservers();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
            gameBoard.setState(GameState.PAUSED);

        notifyStateObservers();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
