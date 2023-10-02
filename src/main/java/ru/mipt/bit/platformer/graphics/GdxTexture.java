package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class GdxTexture {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    public GdxTexture(String textureName) {
        texture = new Texture(textureName);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
