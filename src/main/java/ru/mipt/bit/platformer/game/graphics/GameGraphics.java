package ru.mipt.bit.platformer.game.graphics;

import ru.mipt.bit.platformer.game.Entity;

public interface GameGraphics {
    /*
    port
     */
    void addEntity(Entity entity);

    void removeEntity(Entity entity);

    void render();

    float getDeltaTime();

    void dispose();

    void toggleRenderHealthbar();
}
