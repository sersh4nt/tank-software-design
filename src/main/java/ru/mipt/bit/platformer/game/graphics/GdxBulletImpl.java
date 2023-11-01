package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.entity.Bullet;
import ru.mipt.bit.platformer.game.graphics.util.TileMovement;

import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.drawTextureRegionUnscaled;


public class GdxBulletImpl implements Renderable {
    /*
    adapter
     */
    private final Bullet bullet;
    private final TileMovement tileMovement;
    private final GdxTexture texture;

    public GdxBulletImpl(Bullet bullet, TileMovement tileMovement, String textureName) {
        this.bullet = bullet;
        this.tileMovement = tileMovement;
        texture = new GdxTexture(textureName);
    }

    @Override
    public void render(Batch batch) {
        tileMovement.moveRectangleBetweenTileCenters(texture.getRectangle(), bullet.getCoordinates(), bullet.getDestinationCoordinates(), bullet.getMovementProgress());
        drawTextureRegionUnscaled(batch, texture.getTextureRegion(), texture.getRectangle(), bullet.getDirection().getRotation());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Entity getEntity() {
        return bullet;
    }
}
