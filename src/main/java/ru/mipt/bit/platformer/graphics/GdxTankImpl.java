package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.graphics.GdxGameUtils.drawTextureRegionUnscaled;

public class GdxTankImpl implements Renderable {
    private final Tank tank;
    private final TileMovement tileMovement;
    private final GdxTexture texture;

    public GdxTankImpl(Tank tank, GdxTexture texture, TileMovement tileMovement) {
        this.tank = tank;
        this.tileMovement = tileMovement;
        this.texture = texture;
    }

    @Override
    public void render(Batch batch) {
        tileMovement.moveRectangleBetweenTileCenters(texture.getRectangle(), tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
        drawTextureRegionUnscaled(batch, texture.getTextureRegion(), texture.getRectangle(), tank.getDirection().getRotation());
    }

    @Override
    public void dispose() {
        texture.getTexture().dispose();
    }
}
