package eu.kingconquest.framework.io;

import eu.kingconquest.framework.core.Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataWriter {
    public static String save(Game game) {
        try (FileOutputStream fileOut = new FileOutputStream("save.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(game.getGameData());
            return  "Game has been saved successfully!"; // Show notification

        } catch (IOException e) {
            return  "Error saving game data!"; // Show notification
        }
    }


}
