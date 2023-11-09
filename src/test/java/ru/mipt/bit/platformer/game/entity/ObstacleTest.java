package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.GameEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ObstacleTest {
    @Test
    void getCoordinates() {
        var obstacle = new Obstacle(new GridPoint2(0, 0), null);

        assertEquals(obstacle.getCoordinates(), new GridPoint2(0, 0));
    }

    @Test
    void getDestinationCoordinates() {
        var obstacle = new Obstacle(new GridPoint2(0, 0), null);

        assertEquals(obstacle.getCoordinates(), obstacle.getDestinationCoordinates());
    }

    @Test
    void updateState() {
        var obstacle = new Obstacle(new GridPoint2(0, 0), null);
        var before = obstacle.getCoordinates();

        obstacle.updateState(1f);

        assertEquals(before, obstacle.getCoordinates());
    }

    @Test
    void obstacleRemovesWithDamageRecieved() {
        var gameEngine = mock(GameEngine.class);
        var obstacle = new Obstacle(new GridPoint2(0, 0), gameEngine);

        obstacle.damage(1f);

        verify(gameEngine, times(1)).removeEntity(obstacle);
    }
}