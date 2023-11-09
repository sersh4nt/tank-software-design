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
        collisionHandler = new CollisionHandler(10, 8);
        gameEngine = mock(GameEngine.class);
        when(gameEngine.getCollisionHandler()).thenReturn(collisionHandler);
    }

    @Test
    void addCollidable() {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 0f, 1f, 1f, gameEngine);
        var obstacle = new Obstacle(new GridPoint2(1, 1), gameEngine);
        collisionHandler.addCollidable(tank);
        collisionHandler.addCollidable(obstacle);
    }

    @Test
    void tankPositionIsOccupied() {
        var initialPosition = new GridPoint2(0, 0);
        var tank = new Tank(initialPosition, Direction.RIGHT, 0f, 1f, 1f, gameEngine);
        collisionHandler.addCollidable(tank);
        assertNull(collisionHandler.checkCollisionAt(tank, initialPosition));
    }

    @Test
    void testPointIsOccupiedOutsideOfPlayfield() {
        var pt = new GridPoint2(-1, -1);
        assertNull(collisionHandler.checkCollisionAt(null, pt));
    }

    @Test
    void bulletSpawnedOnObstacle() {
        var bullet = new Bullet(new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);
        var obstacle = new Obstacle(new GridPoint2(0, 0), gameEngine);

        collisionHandler.addCollidable(bullet);
        collisionHandler.addCollidable(obstacle);

        var other = collisionHandler.checkCollisionAt(bullet, bullet.getCoordinates());
        assertEquals(obstacle, other);
    }

    @Test
    void bulletSpawnedOnTank() {
        var bullet = new Bullet(new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);
        var tank = new Tank(new GridPoint2(0, 0), Direction.LEFT, 1f, 1f, 1f, gameEngine);

        collisionHandler.addCollidable(bullet);
        collisionHandler.addCollidable(tank);

        var other = collisionHandler.checkCollisionAt(bullet, bullet.getCoordinates());
        assertEquals(tank, other);
    }

    @Test
    void bulletsSpawnedOppositeEachOther() {
        var leftBullet = new Bullet(new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);
        var rightBullet = new Bullet(new GridPoint2(1, 0), Direction.LEFT, 1f, 1f, gameEngine);

        collisionHandler.addCollidable(leftBullet);
        collisionHandler.addCollidable(rightBullet);

        var leftCollision = collisionHandler.checkCollisionAt(leftBullet, leftBullet.getDestinationCoordinates());
        var rightCollision = collisionHandler.checkCollisionAt(rightBullet, rightBullet.getDestinationCoordinates());
        assertEquals(leftCollision, rightBullet);
        assertEquals(rightCollision, leftBullet);
    }
}
