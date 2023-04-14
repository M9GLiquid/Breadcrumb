package eu.kingconquest;

public class MenuActionHandler implements MenuObserver {
    private Sokoban game;

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
