package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.Livable;
import ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils;

public class GdxHealthRenderDecorator extends RenderableDecorator {
    private final GdxGameGraphics graphics;

    public GdxHealthRenderDecorator(Renderable wrappee, GdxGameGraphics graphics) {
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
            var health = livable.getRelativeHealth();
            var healthbarTexture = getHealthbarTexture(health);
            var rectangle = createRectangle();
            GdxGameUtils.drawTextureRegionUnscaled(batch, healthbarTexture, rectangle, 0f);
        }
    }

    private TextureRegion getHealthbarTexture(float relativeHealth) {
        var pixmap = new Pixmap(90, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, 90, 20);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0, 0, (int) (90 * relativeHealth), 20);
        var texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(texture);
    }

    private Rectangle createRectangle() {
        var rectangle = new Rectangle(wrappee.getRectangle());
        rectangle.y += 90;
        return rectangle;
    }
}
