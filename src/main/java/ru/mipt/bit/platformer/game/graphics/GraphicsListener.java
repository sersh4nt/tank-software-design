package ru.mipt.bit.platformer.game.graphics;

import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameListener;

public class GraphicsListener implements GameListener {
    /*
    adapter
     */
    private final GameGraphics graphics;

    public GraphicsListener(GameGraphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void onEntityAdded(Entity entity) {
        graphics.addEntity(entity);
    }

    @Override
    public void onEntityRemoved(Entity entity) {
        graphics.removeEntity(entity);
    }
}
