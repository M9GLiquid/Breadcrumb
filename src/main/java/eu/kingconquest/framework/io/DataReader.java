package eu.kingconquest.framework.io;

import eu.kingconquest.framework.core.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataReader {

    public static String load(Game game) {
        try (FileInputStream fileIn = new FileInputStream(game.getTitle() + "_save.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            game.setGameData((GameData) in.readObject());
            if (game.getGameData() == null)
                return "Save file corrupted!";

            return "Game loaded successfully!"; // Show notification

        } catch (FileNotFoundException e) {
            return "File not found!"; // Show notification

        } catch (IOException e) {
            e.printStackTrace(); // This line can help you trace the error.
            return "Error reading file!"; // Show notification

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // This line can help you trace the error.
            return "GameData class not found!"; // Show notification
        }
    }
}
