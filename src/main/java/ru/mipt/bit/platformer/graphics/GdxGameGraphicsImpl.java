package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.GameState;
import ru.mipt.bit.platformer.entity.Entity;
import ru.mipt.bit.platformer.entity.EntityState;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GdxGameGraphicsImpl implements GameGraphics {
    private final SpriteBatch batch = new SpriteBatch();
    private final Map<Entity, GdxEntityTexture> entityTextureMap = new HashMap<>();
    private MapRenderer mapRenderer;
    private TileMovement tileMovement;
    private TiledMap level;

    @Override
    public void begin() {
        mapRenderer.render();
        batch.begin();
    }

    @Override
    public void end() {
        batch.end();
    }

    @Override
    public void drawEntity(Entity entity, EntityState entityState) {
        var entityTexture = entityTextureMap.get(entity);
        entityTexture.setRectangle(tileMovement.moveRectangleBetweenTileCenters(entityTexture.getRectangle(), entityState.coordinates(), entityState.targetCoordinates(), entityState.movementProgress()));
        drawTextureRegionUnscaled(batch, entityTexture.getRegion(), entityTexture.getRectangle(), entityState.direction().getRotation());
    }

    @Override
    public void dispose() {
        for (var entry : entityTextureMap.values()) {
            entry.getTexture().dispose();
        }
        level.dispose();
        batch.dispose();
    }

    @Override
    public void addEntityTexture(Entity entity, EntityTexture entityTexture) {
        entityTextureMap.put(entity, (GdxEntityTexture) entityTexture);
    }

    @Override
    public void loadLevel(String levelName) {
        level = new TmxMapLoader().load(levelName);
        mapRenderer = createSingleLayerMapRenderer(level, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    @Override
    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public GdxEntityTexture createTexture(String textureName) {
        var texture = new Texture(textureName);
        return new GdxEntityTexture(texture);
    }

    @Override
    public void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void renderGame(GameState gameState) {
        begin();
        for (var entry : gameState.getEntityStates().entrySet()) {
            drawEntity(entry.getKey(), entry.getValue());
        }
        end();
    }
}
