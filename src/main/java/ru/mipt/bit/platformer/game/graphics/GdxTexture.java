package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils;

public class GdxTexture {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    public GdxTexture(String textureName) {
        texture = new Texture(textureName);
        textureRegion = new TextureRegion(texture);
        rectangle = GdxGameUtils.createBoundingRectangle(textureRegion);
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
