package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.entity.state.HeavyTankState;
import ru.mipt.bit.platformer.game.entity.state.LightTankState;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TankTest {
    private Tank createTank() {
        return new Tank(new GridPoint2(0, 0), Direction.RIGHT, new LightTankState(1f, 100f, 100f, 2f), null);
    }

    private Tank createTank(GameEngine gameEngine) {
        return new Tank(new GridPoint2(0, 0), Direction.RIGHT, new LightTankState(1f, 100f, 100f, 2f), gameEngine);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankStartsMove_thenUpdatesDestinationCoordinates(Direction direction) {
        var tank = createTank();
        var source = tank.getCoordinates();
        var destination = direction.apply(source);

        tank.moveTo(direction);
        assertEquals(tank.getDestinationCoordinates(), destination);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankUpdatesState_thenTankMoves(Direction direction) {
        var tank = createTank();

        tank.moveTo(direction);
        tank.updateState(0.1f);

        assertTrue(tank.isMoving());
        assertNotEquals(tank.getMovementProgress(), 1f);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankCompletesMove_thenTankSetsDestinationCoordinates(Direction direction) {
        var tank = createTank();
        var destination = direction.apply(tank.getCoordinates());

        tank.moveTo(direction);
        tank.updateState(1f);

        assertEquals(tank.getCoordinates(), destination);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void rotate(Direction direction) {
        var tank = createTank();

        tank.rotate(direction);

        assertSame(tank.getDirection(), direction);
    }

    @Test
    void getCoordinates() {
        var tank = createTank();

        assertEquals(tank.getCoordinates(), new GridPoint2(0, 0));
    }


    @Test
    void destroyedTankCannotShoot() {
        var gameEngine = mock(GameEngine.class);
        var destroyedTank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, new HeavyTankState(1f, 0f, 0f, 2f), gameEngine);

        destroyedTank.shoot();

        verify(gameEngine, never()).addEntity(any());
    }

    @Test
    void tankCannotShootUntilReloaded() {
        var gameEngine = mock(GameEngine.class);
        var tank = createTank(gameEngine);

        tank.shoot();
        tank.shoot();

        verify(gameEngine, times(1)).addEntity(any());
    }

    @Test
    void tankDestroysIfDamageIsMoreThanHealth() {
        var gameEngine = mock(GameEngine.class);
        var tank = createTank(gameEngine);

        tank.damage(300f);

        verify(gameEngine, times(1)).removeEntity(tank);
    }
}
