package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BulletTest {
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testBulletKeepsMoving(Direction direction) {
        var startPosition = new GridPoint2(0, 0);
        var bullet = new Bullet(null, startPosition, direction, 1f, 1f, null);

        bullet.updateState(1f);

        assertEquals(bullet.getCoordinates(), direction.apply(startPosition));
    }

    @Test
    public void testBulletCollides() {
        var obstacle = mock(Collidable.class);
        var collisionHandler = mock(CollisionHandler.class);
        var gameEngine = mock(GameEngine.class);
        when(gameEngine.getCollisionHandler()).thenReturn(collisionHandler);
        when(collisionHandler.checkCollisionAt(any(), any(), any())).thenReturn(obstacle);

        var bullet = new Bullet(null, new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);

        bullet.updateState(2f);

        verify(gameEngine, times(1)).removeEntity(bullet);
    }

    @Test
    public void testBulletDamagesLivable() {
        var obstacle = mock(Obstacle.class);
        var collisionHandler = mock(CollisionHandler.class);
        var gameEngine = mock(GameEngine.class);
        when(gameEngine.getCollisionHandler()).thenReturn(collisionHandler);
        when(collisionHandler.checkCollisionAt(any(), any(), any())).thenReturn(obstacle);

        var bullet = new Bullet(null, new GridPoint2(0, 0), Direction.RIGHT, 1f, 1f, gameEngine);

        bullet.updateState(1f);

        verify(obstacle, times(1)).damage(1f);
    }
}