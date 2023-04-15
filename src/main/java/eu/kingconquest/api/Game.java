package eu.kingconquest.api;

import eu.kingconquest.Core.GameObserver;

public interface Game {
    void initiate();
    void start();
    void reset();
    boolean isFinished();
    void registerObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
}
