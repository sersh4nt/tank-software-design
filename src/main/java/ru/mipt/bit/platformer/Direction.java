package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    RIGHT(0),
    UP(90),
    LEFT(-180),
    DOWN(-90),
    NONE(1000000);

    public final float angle;

    Direction(int angle) {
        this.angle = angle;
    }

    public GridPoint2 movePoint(GridPoint2 pt) {
        switch (this) {
            case UP -> {
                return new GridPoint2(pt.x, pt.y + 1);
            }
            case RIGHT -> {
                return new GridPoint2(pt.x + 1, pt.y);
            }
            case LEFT -> {
                return new GridPoint2(pt.x - 1, pt.y);
            }
            case DOWN -> {
                return new GridPoint2(pt.x, pt.y - 1);
            }
        }
        return pt;
    }
}
