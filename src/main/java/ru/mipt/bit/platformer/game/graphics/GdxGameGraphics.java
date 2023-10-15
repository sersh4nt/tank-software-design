package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.game.entity.Entity;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.graphics.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.getSingleLayer;

public class GdxGameGraphics {
    private final Batch batch = new SpriteBatch();
    private final List<Renderable> renderables = new ArrayList<>();
    private final TileMovement tileMovement;
    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;

    public GdxGameGraphics(String levelName) {
        level = new TmxMapLoader().load(levelName);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        levelRenderer = createSingleLayerMapRenderer(level, batch);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void render() {
        clearScreen();
        levelRenderer.render();

        batch.begin();
        for (var i : renderables) {
            i.render(batch);
        }
        batch.end();
    }

    public void addEntity(Entity entity) {
        var renderable = getRenderableFromEntity(entity);
        renderables.add(renderable);
    }

    private Renderable getRenderableFromEntity(Entity entity) {
        if (entity instanceof Obstacle obstacle) {
            return new GdxTreeImpl(obstacle, groundLayer);
        }
        if (entity instanceof Tank tank) {
            return new GdxTankImpl(tank, tileMovement);
        }
        return null;
    }

    public void dispose() {
        for (var renderable : renderables) {
            renderable.dispose();
        }
        level.dispose();
    }
}
