package eu.kingconquest.sokoban.Listener;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.GameView;
import eu.kingconquest.framework.ui.View;
import eu.kingconquest.sokoban.ui.StartMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {
    private final Game game;
    private final View view;

    private final MenuItem item;

    public MenuListener(Game game, View view, MenuItem item){
        this.game = game;
        this.view = view;
        this.item = item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(item){
            case SAVE -> save();
            case LOAD -> load();
            case START -> start();
            case RETURN -> back();
            case RESUME -> resume();
            case EXIT -> exit();
        }
    }


    private void save(){
        game.save();
    }

    private void load(){
        game.load();
    }

    private void start(){
        game.setState(GameState.INITIATING);
        game.getController().notifyObservers();
        GameView gameView = new GameView(game);
        game.getGameFrame().addView(gameView, game.getBoard().COLS, game.getBoard().ROWS);
        game.getGameFrame().view = gameView;

        game.start();
    }


    private void back() {
        game.getGameFrame().remove(view);
        game.getGameFrame().addView(new StartMenu(game), 30, 20);
    }

    private void resume(){
        GameFrame gameFrame = game.getGameFrame();
        gameFrame.remove(view);
        game.setState(GameState.RUNNING);
        gameFrame.addView(gameFrame.view, game.getBoard().COLS, game.getBoard().ROWS);
    }

    private void exit(){
        System.exit(0);
    }
}
