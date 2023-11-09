package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.GameListener;
import ru.mipt.bit.platformer.game.entity.CollisionHandler;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.entity.state.LightTankState;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLevelGenerator implements LevelGenerator {
    /*
    adapter
     */
    private static final char TREE = 'T';
    private static final char PLAYER = 'X';
    private final String filename;
    private List<String> fileContent = null;

    public FileLevelGenerator(String filename) {
        this.filename = filename;
    }

    @Override
    public GameEngine loadLevel(CollisionHandler collisionHandler, GameListener listener) {
        try {
            fileContent = readFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("UNABLE TO FIND LEVEL DESCRIPTION FILE: %s");
            return null;
        }

        var gameEngine = new GameEngine(collisionHandler, fileContent.get(0).length(), fileContent.size(), listener);

        generatePlayer(gameEngine);
        generateObstacles(gameEngine);

        return gameEngine;
    }

    private void generatePlayer(GameEngine gameEngine) {
        var playerPosition = getCharPositions(fileContent, PLAYER).get(0);
        var player = new Tank(playerPosition, Direction.RIGHT, new LightTankState(2f, 100f, 100f, 1f), gameEngine);
        gameEngine.setPlayer(player);
    }

    private void generateObstacles(GameEngine gameEngine) {
        var obstaclePositions = getCharPositions(fileContent, TREE);
        for (var position : obstaclePositions) {
            var obstacle = new Obstacle(position, gameEngine);
            gameEngine.addEntity(obstacle);
        }
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
