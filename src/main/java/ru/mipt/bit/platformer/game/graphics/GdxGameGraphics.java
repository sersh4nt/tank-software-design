package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.entity.Bullet;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.graphics.util.TileMovement;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.game.graphics.util.GdxGameUtils.getSingleLayer;

public class GdxGameGraphics implements GameGraphics {
    /*
    adapter
     */
    private final Batch batch = new SpriteBatch();
    private final Map<Entity, Renderable> renderables = new HashMap<>();
    private final TileMovement tileMovement;
    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private boolean renderHealthbar = false;

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

    @Override
    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render() {
        clearScreen();
        levelRenderer.render();

        batch.begin();
        renderables.values().forEach(renderable -> renderable.render(batch));
        batch.end();
    }

    @Override
    public void addEntity(Entity entity) {
        var renderable = getRenderableFromEntity(entity);
        renderables.put(entity, renderable);
    }

    @Override
    public void removeEntity(Entity entity) {
        var renderable = renderables.remove(entity);
        renderable.dispose();
    }

    private Renderable getRenderableFromEntity(Entity entity) {
        Renderable renderable = null;
        if (entity instanceof Obstacle obstacle) {
            renderable = new GdxTreeImpl(obstacle, groundLayer, "images/greenTree.png");
        }
        if (entity instanceof Tank tank) {
            renderable = new GdxTankImpl(tank, tileMovement, "images/tank_blue.png");
        }
        if (entity instanceof Bullet bullet) {
            renderable = new GdxBulletImpl(bullet, tileMovement, "images/bullet.png");
        }
        renderable = new GdxHealthRenderDecorator(renderable, this);
        return renderable;
    }

    @Override
    public void dispose() {
        renderables.values().forEach(Disposable::dispose);
        level.dispose();
        batch.dispose();
    }

    @Override
    public void toggleRenderHealthbar() {
        renderHealthbar = !renderHealthbar;
        System.out.println("toggling healthbar rendering");
    }

    public boolean shouldRenderHealthbar() {
        return renderHealthbar;
    }
}
