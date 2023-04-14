package eu.kingconquest.Core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final Game game;
    private final List<GameObserver> observers;

    public GameController(Game game) {
        this.game = game;
        observers = new ArrayList<>();
    }
    public static Image loadImage(String imageName) {
        URL imageURL = GameController.class.getClassLoader().getResource(imageName);
        try {
            assert imageURL != null;
            return ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    // Implement game control methods, e.g. moving pieces and notifying observers
}
