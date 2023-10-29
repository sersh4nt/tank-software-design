package ru.mipt.bit.platformer.game.commands;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomEntityController implements EntityController {
    private final static Random random = new Random();
    private final Map<Entity, Command> previousCommands = new HashMap<>();

    @Override
    public Map<Entity, Command> getCommands(GameEngine engine) {
        var result = new HashMap<Entity, Command>();
        engine.getEnemies().forEach(enemy -> {
            var prev = previousCommands.getOrDefault(enemy, null);
            if (prev instanceof MoveCommand && random.nextBoolean()) {
                result.put(enemy, prev);
                return;
            }
            var command = getRandomCommand();
            previousCommands.put(enemy, command);
            result.put(enemy, command);
        });
        return result;
    }

    private Command getRandomCommand() {
        if (random.nextInt(5) == 0) {
            return new ShootCommand();
        }
        return new MoveCommand(getRandomDirection());
    }

    private Direction getRandomDirection() {
        var constants = Direction.class.getEnumConstants();
        var idx = random.nextInt(constants.length);
        return constants[idx];
    }
}
