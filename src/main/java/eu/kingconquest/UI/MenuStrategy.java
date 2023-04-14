package eu.kingconquest.UI;

import eu.kingconquest.Core.Sokoban;

import javax.swing.*;

public interface MenuStrategy {
    JPanel createMenu(Sokoban game);

    void registerObserver(MenuObserver observer);

    void removeObserver(MenuObserver observer);
}
/*
    * Created if we would like more kind of menus
 */