package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.entity.Obstacle;

import static ru.mipt.bit.platformer.graphics.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.graphics.GdxGameUtils.moveRectangleAtTileCenter;

public class GdxTreeImpl implements Renderable {
    private final GdxTexture texture;

    public GdxTreeImpl(Obstacle obstacle, GdxTexture texture, TiledMapTileLayer groundLayer) {
        this.texture = texture;
        moveRectangleAtTileCenter(groundLayer, texture.getRectangle(), obstacle.getCoordinates());
    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, texture.getTextureRegion(), texture.getRectangle(), 0f);
    }

    @Override
    public void dispose() {
        texture.getTexture().dispose();
    }
}
