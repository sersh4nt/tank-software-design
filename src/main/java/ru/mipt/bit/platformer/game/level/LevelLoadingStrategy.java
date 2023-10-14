package ru.mipt.bit.platformer.game.level;

import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.entity.Entity;
import ru.mipt.bit.platformer.game.entity.Tank;

import java.util.List;

public interface LevelLoadingStrategy {
    GameEngine loadLevel();

    Tank getPlayer();

    List<Entity> getEntities();
}
