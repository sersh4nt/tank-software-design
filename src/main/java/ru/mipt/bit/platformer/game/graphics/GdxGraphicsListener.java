package ru.mipt.bit.platformer.game.graphics;

import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameListener;

public class GdxGraphicsListener implements GameListener {
    private final GdxGameGraphics graphics;

    public GdxGraphicsListener(GdxGameGraphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void onEntityAdded(Entity entity) {
        graphics.addEntity(entity);
    }
}
