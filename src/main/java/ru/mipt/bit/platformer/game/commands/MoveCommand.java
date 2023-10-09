package ru.mipt.bit.platformer.game.commands;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.entity.Entity;
import ru.mipt.bit.platformer.game.entity.Movable;

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
