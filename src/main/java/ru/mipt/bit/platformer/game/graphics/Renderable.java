package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.game.Entity;

public interface Renderable extends Disposable {
    void render(Batch batch);

    Entity getEntity();
}
