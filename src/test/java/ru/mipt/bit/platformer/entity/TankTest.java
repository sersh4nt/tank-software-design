package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.mipt.bit.platformer.util.Direction;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {
    private Tank tank;

    @BeforeEach
    void setUp() {
        tank = new Tank(new GridPoint2(0, 0), Direction.RIGHT, 1.0f);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankStartsMove_thenUpdatesDestinationCoordinates(Direction direction) {
        var source = tank.getCoordinates();
        var destination = direction.apply(source);

        tank.moveTo(direction);
        assertEquals(tank.getDestinationCoordinates(), destination);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankUpdatesState_thenTankMoves(Direction direction) {
        tank.moveTo(direction);
        tank.updateState(0.1f);
        assertTrue(tank.isMoving());
        assertNotEquals(tank.getMovementProgress(), 1f);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void givenDirection_whenTankCompletesMove_thenTankSetsDestinationCoordinates(Direction direction) {
        var destination = direction.apply(tank.getCoordinates());
        tank.moveTo(direction);
        tank.updateState(1f);
        assertEquals(tank.getCoordinates(), destination);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void rotate(Direction direction) {
        tank.rotate(direction);
        assertSame(tank.getDirection(), direction);
    }

    @Test
    void getCoordinates() {
        assertEquals(tank.getCoordinates(), new GridPoint2(0, 0));
    }
}