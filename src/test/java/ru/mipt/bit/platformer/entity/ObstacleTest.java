package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObstacleTest {
    private Obstacle obstacle;

    @BeforeEach
    void setUp() {
        obstacle = new Obstacle(new GridPoint2(0, 0));
    }

    @Test
    void getCoordinates() {
        assertEquals(obstacle.getCoordinates(), new GridPoint2(0, 0));
    }

    @Test
    void getDestinationCoordinates() {
        assertEquals(obstacle.getCoordinates(), obstacle.getDestinationCoordinates());
    }

    @Test
    void updateState() {
        var before = obstacle.getCoordinates();

        obstacle.updateState(1f);

        assertEquals(before, obstacle.getCoordinates());
    }
}