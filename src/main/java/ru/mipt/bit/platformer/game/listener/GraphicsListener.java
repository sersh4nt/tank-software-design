package ru.mipt.bit.platformer.game.listener;

import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameListener;
import ru.mipt.bit.platformer.game.graphics.GdxGameGraphics;

public class GraphicsListener implements GameListener {
    private final GdxGameGraphics graphics;

    public GraphicsListener(GdxGameGraphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void onEntityAdded(Entity entity) {
        graphics.addEntity(entity);
    }
}
