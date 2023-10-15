package ru.mipt.bit.platformer.game.commands;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.entity.Tank;

import static org.mockito.Mockito.*;

class MoveCommandTest {
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testMovableCommandApplied(Direction direction) {
        var command = new MoveCommand(direction);
        var entity = mock(Tank.class);

        command.apply(entity);

        verify(entity, times(1)).moveTo(direction);
    }
}