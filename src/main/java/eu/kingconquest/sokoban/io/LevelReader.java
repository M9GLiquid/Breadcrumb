package eu.kingconquest.sokoban.io;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.sokoban.core.SokobanEntityType;
import eu.kingconquest.sokoban.entities.Crate;
import eu.kingconquest.sokoban.entities.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class LevelReader {
    public static void loadLevel(String fileName, Game game, int targetLevel) {
        File file = new File(Objects.requireNonNull(LevelReader.class.getClassLoader().getResource("io/levels.txt")).getFile());
        Scanner sc;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
            return;
        }

        int currentLevel;
        int numRows = 0;
        int numCols = 0;
        int row = 0;
        boolean readingLevel = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.startsWith("Level ")) {
                currentLevel = Integer.parseInt(line.substring(6).replace(":", ""));
                readingLevel = currentLevel == targetLevel;
                if (readingLevel) {
                    numRows = 0;
                    numCols = 0;
                }
            } else if (readingLevel) {
                if (line.isEmpty())
                    break;

                numRows++;
                numCols = Math.max(numCols, line.length());
            }
        }

        game.getBoard().grid = new Tile[numRows][numCols];
        game.getBoard().COLS = numCols;
        game.getBoard().ROWS = numRows;


        // Reset the scanner
        sc.close();
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
            return;
        }

        readingLevel = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.startsWith("Level ")) {
                currentLevel = Integer.parseInt(line.substring(6).replace(":", ""));
                readingLevel = currentLevel == targetLevel;
            } else if (readingLevel) {
                if (line.isEmpty())
                    break;

                processLine(game, line, row);
                row++;
            }
        }
        sc.close();
    }



    private static void processLine(Game game, String line, int row) {
        for (int col = 0; col < line.length(); col++) {
            char c = line.charAt(col);
            Location location = new Location(col, row);

            switch (c) {
                case 'P' -> {
                    game.addEntity(new Player(location));
                    game.getBoard().grid[row][col] = new Tile(location, SokobanEntityType.GROUND, true);
                }
                case '#' -> game.getBoard().grid[row][col] = new Tile(location, SokobanEntityType.WALL, false);
                case 'X' -> game.getBoard().grid[row][col] = new Tile(location, SokobanEntityType.GROUND_MARKED, true);
                case 'C' -> {
                    game.addEntity(new Crate(location));
                    game.getBoard().grid[row][col] = new Tile(location, SokobanEntityType.GROUND, true);
                }
                case '-' -> game.getBoard().grid[row][col] = new Tile(location, SokobanEntityType.GROUND, true);
            }
        }
    }
}
