package ru.mipt.bit.platformer.game;

import ru.mipt.bit.platformer.game.entity.Entity;

public interface Command {
    void apply(Entity entity);
}
