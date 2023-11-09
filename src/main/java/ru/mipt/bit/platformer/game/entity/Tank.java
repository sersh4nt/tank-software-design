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
    private final GameEngine gameEngine;
    private final float initialHealth;
    private float lastShootedAt = 0f;
    private float health;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private GridPoint2 coordinates;
    private float movementProgress = MOVEMENT_COMPLETED;

    public Tank(GridPoint2 coordinates, Direction direction, float movementSpeed, float health, float reloadTime, GameEngine gameEngine) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.movementSpeed = movementSpeed;
        destinationCoordinates = coordinates;
        this.health = health;
        initialHealth = health;
        this.reloadTime = reloadTime;
        this.gameEngine = gameEngine;
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }


    public void moveTo(Direction direction) {
        if (isMoving()) {
            return;
        }

        var newDestination = direction.apply(coordinates);
        var collisionHandler = gameEngine == null ? null : gameEngine.getCollisionHandler();
        if (collisionHandler == null
                || collisionHandler.checkCollisionAt(this, newDestination) == null
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
        lastShootedAt -= deltaTime;
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
        if (!isAbleToShoot()) return;

        var bullet = new Bullet(direction.apply(coordinates), direction, 20f, 5f, gameEngine);
        gameEngine.addEntity(bullet);
        lastShootedAt = reloadTime;
    }

    private boolean isAbleToShoot() {
        if (health <= 0) return false;

        return lastShootedAt <= 0;
    }

    @Override
    public void damage(float damage) {
        health -= damage;
        if (health <= 0) {
            gameEngine.removeEntity(this);
        }
    }

    @Override
    public float getRelativeHealth() {
        return health / initialHealth;
    }
}
