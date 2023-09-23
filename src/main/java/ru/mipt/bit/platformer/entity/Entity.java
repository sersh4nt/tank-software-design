package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Direction;

public class Entity {
    protected GridPoint2 coordinates;
    protected Direction direction = Direction.RIGHT;

    public Entity(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public Entity(GridPoint2 coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }
}
