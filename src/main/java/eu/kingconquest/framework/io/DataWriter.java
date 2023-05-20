package eu.kingconquest.framework.io;

import eu.kingconquest.framework.core.Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The DataWriter class provides functionality for writing GameData objects to a file.
 * The class contains a static method for saving game data to a file,
 * serializing the data from a GameData object.
 */
public class DataWriter {

    /**
     * Saves the current state of the game into a file by serializing the game's GameData object.
     *
     * @param game the game instance for which game data is to be saved
     * @return a string message indicating the success or failure of the operation
     */
    public static String save(Game game) {
        try (FileOutputStream fileOut = new FileOutputStream(game.getTitle() + "_save.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(game.getGameData());
            return  "Game saved!"; // Show notification

        } catch (IOException e) {
            return  "Error saving game data!"; // Show notification
        }
    }
}
