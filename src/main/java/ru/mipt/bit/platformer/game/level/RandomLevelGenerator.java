package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.GameListener;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.entity.state.LightTankState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLevelGenerator implements LevelGenerator {
    /*
    adapter
     */
    private final Random random = new Random();
    private final int width;
    private final int height;

    public RandomLevelGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public GameEngine loadLevel(GameListener listener) {
        var gameEngine = new GameEngine(width, height, listener);
        var player = generatePlayer(gameEngine);
        generateObstacles(gameEngine, player);
        return gameEngine;
    }

    private Tank generatePlayer(GameEngine gameEngine) {
        var playerPosition = generateRandomPosition();
        var player = new Tank(playerPosition, Direction.RIGHT, new LightTankState(2f, 100f, 100f, 1f), gameEngine);
        gameEngine.setPlayer(player);
        return player;
    }

    private void generateObstacles(GameEngine gameEngine, Tank player) {
        var obstaclePositions = generateObstaclePositions(player.getCoordinates());
        for (var position : obstaclePositions) {
            var obstacle = new Obstacle(position, gameEngine);
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
