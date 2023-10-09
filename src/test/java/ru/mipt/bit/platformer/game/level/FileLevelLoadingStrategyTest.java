package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLevelLoadingStrategyTest {
    @Test
    void loadLevel() {
        var playerPosition = new GridPoint2(5, 4);
        var obstacles = Arrays.asList(
                new GridPoint2(3, 0),
                new GridPoint2(6, 0),
                new GridPoint2(2, 1),
                new GridPoint2(3, 1),
                new GridPoint2(6, 1),
                new GridPoint2(7, 1),
                new GridPoint2(8, 1),
                new GridPoint2(9, 1),
                new GridPoint2(9, 2),
                new GridPoint2(0, 3),
                new GridPoint2(1, 3),
                new GridPoint2(2, 3),
                new GridPoint2(3, 3),
                new GridPoint2(6, 3),
                new GridPoint2(9, 3)
        );
        FileLevelLoadingStrategy ls = new FileLevelLoadingStrategy("map.txt");

        var ld = ls.loadLevel();

        assertEquals(playerPosition, ld.playerPosition);
        assertArrayEquals(obstacles.toArray(), ld.obstacles.toArray());
    }
}