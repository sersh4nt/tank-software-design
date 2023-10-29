package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.Direction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionHandlerTest {
    private CollisionHandler collisionHandler;

    @BeforeEach
    void setUp() {
        collisionHandler = new CollisionHandler(10, 8);
    }

    @Test
    void addCollidable() {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 0f, 1f, 1f, collisionHandler);
        var obstacle = new Obstacle(new GridPoint2(1, 1));
        collisionHandler.addCollidable(tank);
        collisionHandler.addCollidable(obstacle);
    }

    @Test
    void tankPositionIsOccupied() {
        var initialPosition = new GridPoint2(0, 0);
        var tank = new Tank(initialPosition, Direction.RIGHT, 0f, 1f, 1f, collisionHandler);
        collisionHandler.addCollidable(tank);
        assertFalse(collisionHandler.getCollidablesAt(initialPosition).isEmpty());
    }

    @Test
    void testPointIsOccupiedOutsideOfPlayfield() {
        var pt = new GridPoint2(-1, -1);
        assertTrue(collisionHandler.getCollidablesAt(pt).isEmpty());
    }
}