package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLevelLoadingStrategy implements LevelLoadingStrategy {
    private final Random random = new Random();
    private final int width;
    private final int height;

    public RandomLevelLoadingStrategy(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public LevelDescription loadLevel() {
        var playerPosition = generateRandomPosition();
        var obstacles = generateObstaclePositions(playerPosition);
        return new LevelDescription(obstacles, playerPosition);
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
