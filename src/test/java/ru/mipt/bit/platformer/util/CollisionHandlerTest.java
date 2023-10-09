package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.entity.CollisionHandler;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionHandlerTest {
    private CollisionHandler collisionHandler;

    @BeforeEach
    void setUp() {
        collisionHandler = new CollisionHandler();
    }

    @Test
    void addCollidable() {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 0f, collisionHandler);
        var obstacle = new Obstacle(new GridPoint2(1, 1));
        collisionHandler.addCollidable(tank);
        collisionHandler.addCollidable(obstacle);
    }

    @Test
    void tankPosition_isOccupied() {
        var initialPosition = new GridPoint2(0, 0);
        var tank = new Tank(initialPosition, Direction.RIGHT, 0f, collisionHandler);
        collisionHandler.addCollidable(tank);
        assertTrue(collisionHandler.isOccupied(initialPosition));
    }
}