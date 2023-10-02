package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.entity.Entity;

public interface Command {
    void apply(Entity entity);
}
