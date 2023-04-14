package eu.kingconquest.Core;

public interface Game {
    void initiate();
    void start();
    void reset();
    boolean isFinished();
    void registerObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
}
