package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TankTest {
    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankStartsMove_thenUpdatesDestinationCoordinates(Direction direction) {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, null);
        var source = tank.getCoordinates();
        var destination = direction.apply(source);

        tank.moveTo(direction);
        assertEquals(tank.getDestinationCoordinates(), destination);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankUpdatesState_thenTankMoves(Direction direction) {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, null);

        tank.moveTo(direction);
        tank.updateState(0.1f);

        assertTrue(tank.isMoving());
        assertNotEquals(tank.getMovementProgress(), 1f);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankCompletesMove_thenTankSetsDestinationCoordinates(Direction direction) {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, null);
        var destination = direction.apply(tank.getCoordinates());

        tank.moveTo(direction);
        tank.updateState(1f);

        assertEquals(tank.getCoordinates(), destination);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void rotate(Direction direction) {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, null);

        tank.rotate(direction);

        assertSame(tank.getDirection(), direction);
    }

    @Test
    void getCoordinates() {
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, null);

        assertEquals(tank.getCoordinates(), new GridPoint2(0, 0));
    }


    @Test
    void destroyedTankCannotShoot() {
        var gameEngine = mock(GameEngine.class);
        var destroyedTank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1f, 0f, 1f, gameEngine);

        destroyedTank.shoot();

        verify(gameEngine, never()).addEntity(any());
    }

    @Test
    void tankCannotShootUntilReloaded() {
        var gameEngine = mock(GameEngine.class);
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, gameEngine);

        tank.shoot();
        tank.shoot();

        verify(gameEngine, times(1)).addEntity(any());
    }

    @Test
    void tankDestroysIfDamageIsMoreThanHealth() {
        var gameEngine = mock(GameEngine.class);
        var tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f, 1f, 1f, gameEngine);

        tank.damage(2f);

        verify(gameEngine, times(1)).removeEntity(tank);
    }
}
