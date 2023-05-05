package eu.kingconquest.framework.io;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.sokoban.io.GameData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataReader {

    public static boolean load(Game game) {
        try (FileInputStream fileIn = new FileInputStream("save.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            game.setGameData((GameData) in.readObject());

            return true; // Show notification

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found!");
            return false; // Show notification
        }
    }
}
