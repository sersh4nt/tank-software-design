package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    RIGHT(new GridPoint2(1, 0), 0),
    LEFT(new GridPoint2(-1, 0), -180),
    UP(new GridPoint2(0, 1), 90),
    DOWN(new GridPoint2(0, -1), -90);

    public final float rotation;
    private final GridPoint2 vector;

    Direction(GridPoint2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }

    public GridPoint2 apply(GridPoint2 point) {
        return point.cpy().add(vector);
    }

    public float getRotation() {
        return rotation;
    }
}
