package eu.kingconquest.framework.strategies;

import eu.kingconquest.framework.observers.GameObserver;

import java.util.HashSet;
import java.util.Set;

public abstract class GameStrategy {
    private final Set<GameObserver> stateObservers = new HashSet<>();
    private final Set<GameObserver> viewObservers = new HashSet<>();
    private final Set<GameObserver> audioObservers = new HashSet<>();

    public void addAudioObserver(GameObserver observer) {
        audioObservers.removeIf(obs -> obs.getClass().equals(observer.getClass()));
        audioObservers.add(observer);
    }

    public void addStateObserver(GameObserver observer) {
        stateObservers.removeIf(obs -> obs.getClass().equals(observer.getClass()));
        stateObservers.add(observer);
    }

    public void addViewObserver(GameObserver observer) {
        viewObservers.removeIf(obs -> obs.getClass().equals(observer.getClass()));
        viewObservers.add(observer);
    }


    public void notifyStateObservers() {
        for (GameObserver observer: stateObservers)
            observer.update();
    }

    public void notifyViewObservers() {
        for (GameObserver observer: viewObservers)
            observer.update();
    }

    public void notifyAudioObservers() {
        for (GameObserver observer : audioObservers)
            observer.update();
    }

    public void clearStateObservers() {
        stateObservers.clear();
    }

    public void clearAudioObservers() {
        audioObservers.clear();
    }

    public void clearViewObservers() {
        viewObservers.clear();
    }


}
