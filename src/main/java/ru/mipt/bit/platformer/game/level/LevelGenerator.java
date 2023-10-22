package ru.mipt.bit.platformer.game.level;

import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.GameListener;

public interface LevelGenerator {
    GameEngine loadLevel(GameListener listener);
}
