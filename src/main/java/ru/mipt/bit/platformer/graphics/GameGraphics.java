package ru.mipt.bit.platformer.graphics;

import ru.mipt.bit.platformer.GameState;
import ru.mipt.bit.platformer.entity.Entity;
import ru.mipt.bit.platformer.entity.EntityState;

public interface GameGraphics {


    void begin();

    void end();

    void drawEntity(Entity entity, EntityState entityState);

    void dispose();

    void addEntityTexture(Entity entity, EntityTexture entityTexture);

    EntityTexture createTexture(String textureName);

    void loadLevel(String levelName);

    float getDeltaTime();

    void clearScreen();

    void renderGame(GameState gameState);
}
