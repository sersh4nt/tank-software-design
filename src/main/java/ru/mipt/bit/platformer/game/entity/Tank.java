package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.continueProgress;


public class Tank implements Entity, Collidable, Movable, Shootable, Livable {
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final float MOVEMENT_STARTED = 0f;
    private final float movementSpeed;
    private final float reloadTime;
    private long lastShootedAt = System.currentTimeMillis();
    private float health;
    private CollisionHandler collisionHandler = null;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private GridPoint2 coordinates;
    private float movementProgress = MOVEMENT_COMPLETED;

    public Tank(GridPoint2 coordinates, Direction direction, float movementSpeed, float health, float reloadTime) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.movementSpeed = movementSpeed;
        destinationCoordinates = coordinates;
        this.health = health;
        this.reloadTime = reloadTime;
    }

    public Tank(GridPoint2 coordinates, Direction direction, float movementSpeed, float health, float reloadTime, CollisionHandler collisionHandler) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.movementSpeed = movementSpeed;
        destinationCoordinates = coordinates;
        this.health = health;
        this.collisionHandler = collisionHandler;
        this.reloadTime = reloadTime;
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }


    public void moveTo(Direction direction) {
        if (isMoving()) {
            return;
        }

        var newDestination = direction.apply(coordinates);
        if (collisionHandler == null
                || collisionHandler.getCollidablesAt(newDestination).isEmpty()
                && !collisionHandler.isOutside(newDestination)) {
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

    @Override
    public void shoot() {
        var currentTime = System.currentTimeMillis();
        if (currentTime - lastShootedAt <= reloadTime) {
            return;
        }

        var bullet = new Bullet(direction.apply(coordinates), direction, 20f, 5f, collisionHandler);
        GameEngine.getInstance().addEntity(bullet);
        lastShootedAt = currentTime;
    }

    @Override
    public void damage(float damage) {
        health -= damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
