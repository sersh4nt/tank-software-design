package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Direction;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;


public class Tank extends Entity {
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final float MOVEMENT_STARTED = 0f;
    private static final float MOVEMENT_SPEED = 0.4f;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;

    public Tank(GridPoint2 coordinates, Direction direction) {
        super(coordinates, direction);
        this.destinationCoordinates = coordinates;
        movementProgress = MOVEMENT_COMPLETED;
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }

    public void moveTo(GridPoint2 destination) {
        destinationCoordinates = destination;
        movementProgress = MOVEMENT_STARTED;
    }

    public void rotate(Direction direction) {
        this.direction = direction;
    }

    public EntityState updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
        }
        return new EntityState(coordinates, destinationCoordinates, direction, movementProgress);
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }
}
