package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.continueProgress;


public class Bullet implements Entity, Movable, Collidable {
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final float MOVEMENT_STARTED = 0f;
    private final Direction direction;
    private final float damage;
    private final float movementSpeed;
    private final CollisionHandler collisionHandler;
    private GridPoint2 destinationCoordinates;
    private GridPoint2 coordinates;
    private float movementProgress;

    public Bullet(GridPoint2 coordinates, Direction direction, float damage, float movementSpeed, CollisionHandler collisionHandler) {
        this.direction = direction;
        this.coordinates = coordinates;
        destinationCoordinates = direction.apply(coordinates);
        this.damage = damage;
        movementProgress = MOVEMENT_STARTED;
        this.movementSpeed = movementSpeed;
        this.collisionHandler = collisionHandler;
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
        if (checkCollisions(coordinates)) return;

        if (checkCollisions(destinationCoordinates)) return;

        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
            moveTo(direction);
        }
    }

    private boolean checkCollisions(GridPoint2 position) {
        var hasCollided = false;

        if (collisionHandler == null) {
            return hasCollided;
        }

        var gameEngine = GameEngine.getInstance();

        if (collisionHandler.isOutside(position)) {
            gameEngine.removeEntity(this);
            hasCollided = true;
            return hasCollided;
        }

        for (var collidable : collisionHandler.getCollidablesAt(position)) {
            if (collidable == this) continue;

            if (collidable instanceof Livable livable) {
                livable.damage(damage);
                gameEngine.removeEntity(this);
                if (!livable.isAlive()) {
                    gameEngine.removeEntity((Entity) livable);
                }
                hasCollided = true;
                break;
            }

            gameEngine.removeEntity(this);
            gameEngine.removeEntity((Entity) collidable);
            hasCollided = true;
            break;
        }
        return hasCollided;
    }

    @Override
    public void moveTo(Direction direction) {
        movementProgress = MOVEMENT_STARTED;
        destinationCoordinates = direction.apply(coordinates);
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }
}
