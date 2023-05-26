package eu.kingconquest.maze.io;

import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.utils.Location;
import eu.kingconquest.framework.utils.Tile;
import eu.kingconquest.maze.entities.MazePlayer;
import eu.kingconquest.maze.entities.MazeEntityIcon;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class MazeLevelReader {

    public static int loadLevel(String fileName, GameBoard board, int targetLevel) {
        InputStream in = MazeLevelReader.class.getClassLoader().getResourceAsStream("maze/io/" + fileName);
        Scanner sc;

        try {
            assert in != null;
            sc = new Scanner(in);
        } catch (NullPointerException  e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
            return 0;
        }

        int currentLevel;
        int numRows = 0;
        int numCols = 0;
        int row = 0;
        boolean readingLevel = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.toLowerCase().startsWith("level ")) {
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
        // no more levels
        if (!readingLevel)
            return 1;

        board.grid = new Tile[numRows][numCols];
        board.COLS = numCols;
        board.ROWS = numRows;


        // Reset the scanner
        sc.close();
        in = MazeLevelReader.class.getClassLoader().getResourceAsStream("maze/io/" + fileName);
        try {
            assert in != null;
            sc = new Scanner(in);
        } catch (NullPointerException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
            return 0;
        }

        readingLevel = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.toLowerCase().startsWith("level ")) {
                currentLevel = Integer.parseInt(line.substring(6).replace(":", ""));
                readingLevel = currentLevel == targetLevel;
            } else if (readingLevel) {
                if (line.isEmpty())
                    break;

                processLine(board, line, row);
                row++;
            }
        }
        sc.close();
        return 2;
    }


    private static void processLine(GameBoard board, String line, int row) {
        for (int col = 0; col < line.length(); col++) {
            char c = line.charAt(col);
            Location location = new Location(col, row);

            switch (c) {
                case 'P' -> {
                    board.addEntity(new MazePlayer(location));
                    board.grid[row][col] = new Tile(location, MazeEntityIcon.MAZE_GROUND, true);
                }
                case '#' -> board.grid[row][col] = new Tile(location, MazeEntityIcon.MAZE_CRATE, false);
                case 'X' -> {
                    Tile tile = new Tile(location, MazeEntityIcon.MAZE_GROUND_MARKED, true);
                    board.grid[row][col] = tile;
                    board.addEntity(tile);
                }
                case '-' -> board.grid[row][col] = new Tile(location, MazeEntityIcon.MAZE_GROUND, true);
            }
        }
    }
}