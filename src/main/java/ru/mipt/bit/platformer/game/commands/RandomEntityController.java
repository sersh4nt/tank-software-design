package ru.mipt.bit.platformer.game.commands;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Direction;

import java.util.Random;

public class RandomEntityController implements EntityController {
    private final static Random random = new Random();
    private Command previousCommand = null;

    @Override
    public Command getCommand() {
        if (previousCommand != null && random.nextBoolean()) {
            return previousCommand;
        }
        var direction = getRandomDirection();
        previousCommand = new MoveCommand(direction);
        return previousCommand;
    }

    private Direction getRandomDirection() {
        var constants = Direction.class.getEnumConstants();
        var idx = random.nextInt(constants.length);
        return constants[idx];
    }
}
