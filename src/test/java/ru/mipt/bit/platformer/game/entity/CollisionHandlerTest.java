package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CollisionHandlerTest {
    private CollisionHandler collisionHandler;
    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        collisionHandler = new CollisionHandler();
        gameEngine = mock(GameEngine.class);
        when(gameEngine.getCollisionHandler()).thenReturn(collisionHandler);
    }

    @Test
    void addCollidable() {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, null, gameEngine);
        var obstacle = new Obstacle(new GridPoint2(1, 1), gameEngine);
        collisionHandler.onEntityAdded(tank);
        collisionHandler.onEntityAdded(obstacle);
    }

    @Test
    void tankPositionIsOccupied() {
        var initialPosition = new GridPoint2(0, 0);
        var tank = new Tank(initialPosition, Direction.RIGHT, null, gameEngine);
        collisionHandler.onEntityAdded(tank);
        assertNull(collisionHandler.checkCollisionAt(initialPosition, tank));
    }

    @Test
    void testPointIsOccupiedOutsideOfPlayfield() {
        var pt = new GridPoint2(-1, -1);
        assertNull(collisionHandler.checkCollisionAt(pt));
    }

    @Test
    void bulletSpawnedOnObstacle() {
        var bullet = new Bullet(null, new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);
        var obstacle = new Obstacle(new GridPoint2(0, 0), gameEngine);

        collisionHandler.onEntityAdded(bullet);
        collisionHandler.onEntityAdded(obstacle);

        var other = collisionHandler.checkCollisionAt(bullet.getCoordinates(), bullet);
        assertEquals(obstacle, other);
    }

    @Test
    void bulletSpawnedOnTank() {
        var bullet = new Bullet(null, new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);
        var tank = new Tank(new GridPoint2(0, 0), Direction.LEFT, null, gameEngine);

        collisionHandler.onEntityAdded(bullet);
        collisionHandler.onEntityAdded(tank);

        var other = collisionHandler.checkCollisionAt(bullet.getCoordinates(), bullet);
        assertEquals(tank, other);
    }

    @Test
    void bulletsSpawnedOppositeEachOther() {
        var leftBullet = new Bullet(null, new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);
        var rightBullet = new Bullet(null, new GridPoint2(1, 0), Direction.LEFT, 1f, 1f, gameEngine);

        collisionHandler.onEntityAdded(leftBullet);
        collisionHandler.onEntityAdded(rightBullet);

        var leftCollision = collisionHandler.checkCollisionAt(leftBullet.getDestinationCoordinates(), leftBullet);
        var rightCollision = collisionHandler.checkCollisionAt(rightBullet.getDestinationCoordinates(), rightBullet);
        assertEquals(leftCollision, rightBullet);
        assertEquals(rightCollision, leftBullet);
    }
}
