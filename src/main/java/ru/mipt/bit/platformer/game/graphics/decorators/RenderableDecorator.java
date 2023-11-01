package ru.mipt.bit.platformer.game.graphics.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.graphics.Renderable;

public class RenderableDecorator implements Renderable {
    protected Renderable wrappee;

    public RenderableDecorator(Renderable wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void render(Batch batch) {
        wrappee.render(batch);
    }

    @Override
    public Entity getEntity() {
        return wrappee.getEntity();
    }

    @Override
    public void dispose() {
        wrappee.dispose();
    }
}
