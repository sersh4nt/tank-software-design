package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public abstract class Entity implements Drawable {
    private final TextureRegion texture;
    private final Rectangle rectangle;
    private GridPoint2 position;

    protected Entity(TextureRegion texture, GridPoint2 position) {
        this.texture = texture;
        this.position = position;
        this.rectangle = createBoundingRectangle(this.texture);
    }

    public void dispose() {
        texture.getTexture().dispose();
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public void setPosition(GridPoint2 position) {
        this.position = position;
    }

    public TextureRegion getTexture() {
        return texture;
    }


    public Rectangle getRectangle() {
        return rectangle;
    }
}
