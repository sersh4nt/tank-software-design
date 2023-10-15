package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.entity.Entity;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLevelLoadingStrategy implements LevelLoadingStrategy {
    private final Random random = new Random();
    private final int width;
    private final int height;
    private final List<Entity> entities = new ArrayList<>();
    private Tank player = null;

    public RandomLevelLoadingStrategy(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public GameEngine loadLevel() {
        var gameEngine = new GameEngine(width, height);
        generatePlayer(gameEngine);
        generateObstacles(gameEngine);
        return gameEngine;
    }

    @Override
    public Tank getPlayer() {
        return player;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    private void generatePlayer(GameEngine gameEngine) {
        var playerPosition = generateRandomPosition();
        player = new Tank(playerPosition, Direction.RIGHT, 0.4f);
        gameEngine.addEntity(player);
        entities.add(player);
    }

    private void generateObstacles(GameEngine gameEngine) {
        var obstaclePositions = generateObstaclePositions(player.getCoordinates());
        for (var position : obstaclePositions) {
            var obstacle = new Obstacle(position);
            entities.add(obstacle);
            gameEngine.addEntity(obstacle);
        }
    }

    private GridPoint2 generateRandomPosition() {
        return new GridPoint2(random.nextInt(width - 1), random.nextInt(height - 1));
    }

    private List<GridPoint2> generateObstaclePositions(GridPoint2 playerPosition) {
        var result = new ArrayList<GridPoint2>();
        var obstaclesCount = random.nextInt(width * height - 1);
        for (int i = 0; i < obstaclesCount; i++) {
            var obstaclePosition = generateRandomPosition();
            if (obstaclePosition.equals(playerPosition)) continue;
            result.add(new GridPoint2());
        }
        return result;
    }
}
