package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.entity.Entity;
import ru.mipt.bit.platformer.entity.Movable;
import ru.mipt.bit.platformer.util.Direction;

public class MoveCommand implements Command {
    private final Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void apply(Entity entity) {
        if (entity instanceof Movable) {
            ((Movable) entity).moveTo(direction);
        }
    }
}
