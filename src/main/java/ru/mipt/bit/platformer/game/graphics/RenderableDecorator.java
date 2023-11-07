package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.Entity;

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
    public Rectangle getRectangle() {
        return wrappee.getRectangle();
    }

    @Override
    public void dispose() {
        wrappee.dispose();
    }
}
