package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;


public class Tank {
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final float MOVEMENT_STARTED = 0f;
    private static final float MOVEMENT_SPEED = 0.4f;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;
    private Direction direction;

    public Tank(GridPoint2 coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        movementProgress = MOVEMENT_COMPLETED;
        this.direction = direction;
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

    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
        }
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }
}
