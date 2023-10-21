package ru.mipt.bit.platformer.game.level;

import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.GameListener;
import ru.mipt.bit.platformer.game.entity.Tank;

import java.util.List;

public interface LevelLoadingStrategy {
    GameEngine loadLevel(GameListener listener);

    Tank getPlayer();

    List<Entity> getEntities();
}
