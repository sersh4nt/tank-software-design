package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Playfield {
    private static final String MAP_FILENAME = "level.tmx";
    private final TiledMap map;
    private final MapRenderer renderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;

    public Playfield(Batch batch) {
        map = new TmxMapLoader().load(MAP_FILENAME);
        renderer = createSingleLayerMapRenderer(map, batch);
        groundLayer = getSingleLayer(map);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public void renderField() {
        renderer.render();
    }

    public void dispose() {
        map.dispose();
    }

    public void addObstacle(Obstacle obstacle) {
        moveRectangleAtTileCenter(groundLayer, obstacle.getRectangle(), obstacle.getPosition());
    }
}
