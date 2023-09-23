package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Direction;

public record EntityState(GridPoint2 coordinates, GridPoint2 targetCoordinates, Direction direction,
                          float movementProgress) {
    @Override
    public GridPoint2 coordinates() {
        return coordinates;
    }

    @Override
    public GridPoint2 targetCoordinates() {
        return targetCoordinates;
    }

    @Override
    public Direction direction() {
        return direction;
    }

    @Override
    public float movementProgress() {
        return movementProgress;
    }
}
