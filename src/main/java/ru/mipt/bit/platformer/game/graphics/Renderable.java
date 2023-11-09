package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.game.Entity;

public interface Renderable extends Disposable {
    /*
    port
     */
    void render(Batch batch);

    Entity getEntity();

    Rectangle getRectangle();
}
