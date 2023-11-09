package ru.mipt.bit.platformer.game.commands;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.entity.Tank;

import static org.mockito.Mockito.*;

class ShootCommandTest {
    @Test
    public void testApply() {
        var tank = mock(Tank.class);
        var command = new ShootCommand();

        command.apply(tank);

        verify(tank, times(1)).shoot();
    }
}