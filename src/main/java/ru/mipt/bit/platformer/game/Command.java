package ru.mipt.bit.platformer.game;

public interface Command {
    void apply(Entity entity);
}
