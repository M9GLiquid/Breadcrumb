package eu.kingconquest.framework;

public interface GameStrategy {

    void addObserver(GameObserver observer);

    void removeObserver(GameObserver observer);

    void notifyObservers();
}
