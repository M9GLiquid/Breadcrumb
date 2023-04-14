package eu.kingconquest.UI;

import eu.kingconquest.Core.Sokoban;

public class MenuActionHandler implements MenuObserver {
    private final Sokoban game;

    public MenuActionHandler(Sokoban game) {
        this.game = game;
    }

    @Override
    public void onMenuAction(String action) {
        switch (action) {
            case "start" -> game.start();
            case "exit" -> System.exit(0);
            case "resume" -> {} // Nothing Happens
            case "restart" -> game.reset();
        }
    }
}
