package ru.mipt.bit.platformer.game.level;

import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.GameListener;
import ru.mipt.bit.platformer.game.entity.CollisionHandler;

public interface LevelGenerator {
    /*
    port
     */
    GameEngine loadLevel(CollisionHandler collisionHandler, GameListener listener);
}
