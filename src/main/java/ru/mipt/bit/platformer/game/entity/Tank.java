package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.*;
import ru.mipt.bit.platformer.game.entity.state.TankState;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.continueProgress;


public class Tank implements Entity, Collidable, Movable, Shootable, Livable {
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final float MOVEMENT_STARTED = 0f;
    private final GameEngine gameEngine;
    private TankState state;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private GridPoint2 coordinates;
    private float movementProgress = MOVEMENT_COMPLETED;

    public Tank(GridPoint2 coordinates, Direction direction, TankState state, GameEngine gameEngine) {
        this.coordinates = coordinates;
        this.direction = direction;
        destinationCoordinates = coordinates;
        this.gameEngine = gameEngine;
        this.state = state;
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
                || collisionHandler.checkCollisionAt(newDestination, this) == null
                && !gameEngine.isOutside(newDestination)) {
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
        state = state.updateState(deltaTime);

        movementProgress = continueProgress(movementProgress, deltaTime, state.getMovementSpeed());
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
        if (!state.isAbleToShoot()) {
            return;
        }

        state.shoot();
        var bullet = new Bullet(this, direction.apply(coordinates), direction, 20f, 5f, gameEngine);
        gameEngine.addEntity(bullet);
    }

    @Override
    public void damage(float damage) {
        state.damage(damage);
        if (state.getRelativeHealth() <= 0f) {
            gameEngine.removeEntity(this);
        }
    }

    @Override
    public float getRelativeHealth() {
        return state.getRelativeHealth();
    }
}
