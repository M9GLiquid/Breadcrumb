package eu.kingconquest.framework.io;

import eu.kingconquest.framework.core.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The DataReader class provides functionality for reading GameData objects from a file.
 * The class contains a static method for loading game data from a save file,
 * deserializing the data into a GameData object, and setting this object into the game instance.
 */
public class DataReader {

    /**
     * Loads game data from a specified file, deserializes it into a GameData object,
     * and sets this object into the game instance.
     *
     * @param game the game instance for which game data is to be loaded
     * @return a string message indicating the success or failure of the operation
     */
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
