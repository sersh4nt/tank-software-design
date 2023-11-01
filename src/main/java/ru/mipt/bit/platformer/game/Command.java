package ru.mipt.bit.platformer.game;

public interface Command {
    /*
    port
     */
    void apply(Entity entity);
}
