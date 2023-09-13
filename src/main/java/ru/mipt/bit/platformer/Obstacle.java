package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Obstacle extends Entity {

    public Obstacle(TextureRegion texture, GridPoint2 position) {
        super(texture, position);
    }

    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, getTexture(), getRectangle(), 0f);
    }
}
