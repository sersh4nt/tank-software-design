package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.graphics.util.TileMovement;

import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.drawTextureRegionUnscaled;

public class GdxTankImpl implements Renderable {
    private final static String TEXTURE_NAME = "images/tank_blue.png";
    private final Tank tank;
    private final TileMovement tileMovement;
    private final GdxTexture texture;

    public GdxTankImpl(Tank tank, TileMovement tileMovement) {
        this.tank = tank;
        this.tileMovement = tileMovement;
        texture = new GdxTexture(TEXTURE_NAME);
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
