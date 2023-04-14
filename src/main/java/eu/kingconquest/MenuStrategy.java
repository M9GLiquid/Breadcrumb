package eu.kingconquest;

import javax.swing.*;

public interface MenuStrategy {
    JPanel createMenu(Sokoban game);

    void registerObserver(MenuObserver observer);

    void removeObserver(MenuObserver observer);
}
/*
    * Created if we would like more kind of menus
 */