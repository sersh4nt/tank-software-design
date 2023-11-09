package ru.mipt.bit.platformer.game.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomLevelGeneratorTest {
    private LevelGenerator ls;
    private int width;
    private int height;

    @BeforeEach
    void setUp() {
        width = 10;
        height = 8;
        ls = new RandomLevelGenerator(width, height);
    }

    @Test
    void loadLevel() {
        assertNotNull(ls.loadLevel(null, null));
    }

    @Test
    void getPlayer() {
        var engine = ls.loadLevel(null, null);

        assertNotNull(engine.getPlayer());
        assertEntityIsInLevelBounds(engine.getPlayer());
    }

    @Test
    void getEntities() {
        var engine = ls.loadLevel(null, null);

        assertNotNull(engine.getObstacles());
        engine.getObstacles().forEach(this::assertEntityIsInLevelBounds);
    }

    private void assertEntityIsInLevelBounds(Entity entity) {
        if (entity instanceof Collidable collidable) {
            assertTrue(collidable.getCoordinates().x < width);
            assertTrue(collidable.getCoordinates().y < height);
            assertTrue(collidable.getCoordinates().x >= 0);
            assertTrue(collidable.getCoordinates().y >= 0);
        }
    }
}