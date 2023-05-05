package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.core.Game;

public abstract class Menu extends View {

    public Menu(Game game){
        super(game);
        createMenu();
    }
    protected abstract void createMenu();
}
