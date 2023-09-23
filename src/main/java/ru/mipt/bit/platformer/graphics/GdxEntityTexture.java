package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class GdxEntityTexture extends EntityTexture {
    private final Texture texture;
    private final TextureRegion region;
    private Rectangle rectangle;

    public GdxEntityTexture(Texture texture) {
        this.texture = texture;
        region = new TextureRegion(texture);
        rectangle = createBoundingRectangle(region);
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
