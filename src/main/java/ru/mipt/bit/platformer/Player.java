package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Player extends Entity {
    private static final float SPEED = 0.4f;
    private final Rectangle rectangle;
    private float movementProgress = 1f;
    private Direction direction = Direction.RIGHT;
    private GridPoint2 newPosition;

    public Player(TextureRegion texture, GridPoint2 position) {
        super(texture, position);
        newPosition = getPosition();
        this.rectangle = createBoundingRectangle(getTexture());
    }

    public void move(Direction direction, float dTime, Obstacle obstacle, TileMovement movement) {
        updateDirection(direction, obstacle);
        updateCoordinates(dTime, movement);
        if (!isMoving()) {
            setPosition(newPosition);
        }
    }

    private void updateCoordinates(float dTime, TileMovement movement) {
        movement.moveRectangleBetweenTileCenters(rectangle, getPosition(), newPosition, movementProgress);
        movementProgress = continueProgress(movementProgress, dTime, SPEED);
    }

    private void updateDirection(Direction direction, Obstacle obstacle) {
        if (isMoving() || direction == Direction.NONE) {
            return;
        }
        this.direction = direction;
        if (!hasObstacle(direction, obstacle)) {
            newPosition = direction.movePoint(getPosition());
            movementProgress = 0f;
        }
    }

    private boolean hasObstacle(Direction direction, Obstacle obstacle) {
        var newPosition = direction.movePoint(getPosition());
        return newPosition.equals(obstacle.getPosition());
    }

    private boolean isMoving() {
        return !isEqual(movementProgress, 1f);
    }

    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, getTexture(), rectangle, direction.angle);
    }
}
