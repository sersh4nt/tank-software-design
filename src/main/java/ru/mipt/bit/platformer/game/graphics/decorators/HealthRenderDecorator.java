package ru.mipt.bit.platformer.game.graphics.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.game.Livable;
import ru.mipt.bit.platformer.game.graphics.GdxGameGraphics;
import ru.mipt.bit.platformer.game.graphics.Renderable;

public class HealthRenderDecorator extends RenderableDecorator {
    private final GdxGameGraphics graphics;

    public HealthRenderDecorator(Renderable wrappee, GdxGameGraphics graphics) {
        super(wrappee);
        this.graphics = graphics;
    }

    @Override
    public void render(Batch batch) {
        wrappee.render(batch);

        if (graphics.shouldRenderHealthbar()) {
            renderHealthbar(batch);
        }
    }

    private void renderHealthbar(Batch batch) {
        if (wrappee.getEntity() instanceof Livable livable) {
            System.out.printf("drawing healthbar for %s\n", livable.getClass().getName());
        }
    }
}
