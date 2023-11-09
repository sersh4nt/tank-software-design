package ru.mipt.bit.platformer.game.utils;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;

import java.util.Random;

public final class RandomUtils {
    private static final Random random = new Random();

    public static Direction getRandomDirection() {
        var constants = Direction.class.getEnumConstants();
        var idx = random.nextInt(constants.length);
        return constants[idx];
    }

    public static GridPoint2 getRandomPoint(int width, int height) {
        return new GridPoint2(random.nextInt(width - 1), random.nextInt(height - 1));
    }
}
