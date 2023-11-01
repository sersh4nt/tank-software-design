package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.continueProgress;


public class Bullet implements Entity, Collidable {
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final float MOVEMENT_STARTED = 0f;
    private final Direction direction;
    private final float damage;
    private final float movementSpeed;
    private final GameEngine gameEngine;
    private GridPoint2 destinationCoordinates;
    private GridPoint2 coordinates;
    private float movementProgress = MOVEMENT_STARTED;

    public Bullet(GridPoint2 coordinates, Direction direction, float damage, float movementSpeed, GameEngine gameEngine) {
        this.direction = direction;
        this.coordinates = coordinates;
        destinationCoordinates = direction.apply(coordinates);
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.gameEngine = gameEngine;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public void updateState(float deltaTime) {
        if (hasCollisionAt(coordinates)) return;
        if (hasCollisionAt(destinationCoordinates)) return;

        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
            moveTo(direction);
        }
    }

    private boolean hasCollisionAt(GridPoint2 pt) {
        var collidedTo = gameEngine.getCollisionHandler().checkCollisionAt(this, pt);
        if (collidedTo == null) return false;

        if (collidedTo instanceof Livable livable) {
            livable.damage(damage);
        }

        gameEngine.removeEntity(this);
        return true;
    }

    private void moveTo(Direction direction) {
        movementProgress = MOVEMENT_STARTED;
        destinationCoordinates = direction.apply(coordinates);
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }
}
