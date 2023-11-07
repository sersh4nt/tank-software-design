package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.graphics.util.TileMovement;

import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.drawTextureRegionUnscaled;

public class GdxTankImpl implements Renderable {
    /*
    adapter
     */
    private final Tank tank;
    private final TileMovement tileMovement;
    private final GdxTexture texture;

    public GdxTankImpl(Tank tank, TileMovement tileMovement, String textureName) {
        this.tank = tank;
        this.tileMovement = tileMovement;
        texture = new GdxTexture(textureName);
    }

    @Override
    public void render(Batch batch) {
        tileMovement.moveRectangleBetweenTileCenters(texture.getRectangle(), tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
        drawTextureRegionUnscaled(batch, texture.getTextureRegion(), texture.getRectangle(), tank.getDirection().getRotation());
    }

    @Override
    public Entity getEntity() {
        return tank;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Rectangle getRectangle() {
        return texture.getRectangle();
    }
}
