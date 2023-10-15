package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.entity.Entity;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;

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
    private final List<Entity> entities = new ArrayList<>();
    private Tank player = null;
    private List<String> fileContent = null;

    public FileLevelLoadingStrategy(String filename) {
        this.filename = filename;
    }

    @Override
    public GameEngine loadLevel() {
        try {
            fileContent = readFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("UNABLE TO FIND LEVEL DESCRIPTION FILE: %s");
            return null;
        }

        var gameEngine = new GameEngine(fileContent.get(0).length(), fileContent.size());

        generatePlayer(gameEngine);
        generateObstacles(gameEngine);

        return gameEngine;
    }

    private void generatePlayer(GameEngine gameEngine) {
        var playerPosition = getCharPositions(fileContent, PLAYER).get(0);
        player = new Tank(playerPosition, Direction.RIGHT, 0.4f, gameEngine.getCollisionHandler());
        gameEngine.addEntity(player);
        entities.add(player);
    }

    private void generateObstacles(GameEngine gameEngine) {
        var obstaclePositions = getCharPositions(fileContent, TREE);
        for (var position : obstaclePositions) {
            var obstacle = new Obstacle(position);
            entities.add(obstacle);
            gameEngine.addEntity(obstacle);
        }
    }

    @Override
    public Tank getPlayer() {
        return player;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
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
