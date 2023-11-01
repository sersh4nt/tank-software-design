package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils;

public class GdxTreeImpl implements Renderable {
    private final static String TEXTURE_NAME = "images/greenTree.png";
    private final Obstacle obstacle;
    private final GdxTexture texture;

    public GdxTreeImpl(Obstacle obstacle, TiledMapTileLayer groundLayer) {
        this.obstacle = obstacle;
        texture = new GdxTexture(TEXTURE_NAME);
        GdxGameUtils.moveRectangleAtTileCenter(groundLayer, texture.getRectangle(), obstacle.getCoordinates());
    }

    @Override
    public void render(Batch batch) {
        GdxGameUtils.drawTextureRegionUnscaled(batch, texture.getTextureRegion(), texture.getRectangle(), 0f);
    }

    @Override
    public Entity getEntity() {
        return obstacle;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
