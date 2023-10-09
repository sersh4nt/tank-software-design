package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLevelLoadingStrategy implements LevelLoadingStrategy {
    private static final char TREE = 'T';
    private static final char PLAYER = 'X';
    private final String filename;

    public FileLevelLoadingStrategy(String filename) {
        this.filename = filename;
    }

    @Override
    public LevelDescription loadLevel() {
        List<String> fileContent;
        try {
            fileContent = readFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("UNABLE TO FIND LEVEL DESCRIPTION FILE: %s");
            return null;
        }

        var obstacles = getCharPositions(fileContent, TREE);
        var player = getCharPositions(fileContent, PLAYER).get(0);

        return new LevelDescription(obstacles, player);
    }

    private List<String> readFileContent() throws FileNotFoundException {
        var result = new ArrayList<String>();


        var resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            return result;
        }

        File file;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        var scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }
        scanner.close();

        return result;
    }

    private List<GridPoint2> getCharPositions(List<String> fileContent, char symbol) {
        var result = new ArrayList<GridPoint2>();
        for (int y = 0; y < fileContent.size(); y++) {
            for (int x = 0; x < fileContent.get(y).length(); x++) {
                if (fileContent.get(y).charAt(x) == symbol) {
                    result.add(new GridPoint2(x, y));
                }
            }
        }
        return result;
    }
}
