package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.Movable;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.continueProgress;


public class Tank implements Entity, Collidable, Movable {
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final float MOVEMENT_STARTED = 0f;
    private final float movementSpeed;
    private CollisionHandler collisionHandler = null;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private GridPoint2 coordinates;
    private float movementProgress = MOVEMENT_COMPLETED;

    public Tank(GridPoint2 coordinates, Direction direction, float movementSpeed) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.movementSpeed = movementSpeed;
        destinationCoordinates = coordinates;
    }

    public Tank(GridPoint2 coordinates, Direction direction, float movementSpeed, CollisionHandler collisionHandler) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.movementSpeed = movementSpeed;
        destinationCoordinates = coordinates;
        this.collisionHandler = collisionHandler;
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }


    public void moveTo(Direction direction) {
        if (isMoving()) {
            return;
        }

        var newDestination = direction.apply(coordinates);
        if (collisionHandler == null || !collisionHandler.isOccupied(newDestination)) {
            movementProgress = MOVEMENT_STARTED;
            destinationCoordinates = newDestination;
        }

        rotate(direction);
    }


    public void rotate(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
        }
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public Direction getDirection() {
        return direction;
    }
}
