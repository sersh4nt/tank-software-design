package ru.mipt.bit.platformer.game;

public interface GameListener {
    /*
    port
     */
    void onEntityAdded(Entity entity);

    void onEntityRemoved(Entity entity);
}
