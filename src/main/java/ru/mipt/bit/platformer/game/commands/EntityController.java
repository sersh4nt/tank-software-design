package ru.mipt.bit.platformer.game.commands;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameEngine;

import java.util.Map;

public interface EntityController {
    /*
    port
     */
    Map<Entity, Command> getCommands(GameEngine engine);
}
