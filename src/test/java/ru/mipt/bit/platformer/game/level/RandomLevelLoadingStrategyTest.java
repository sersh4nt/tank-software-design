package ru.mipt.bit.platformer.game.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomLevelLoadingStrategyTest {
    private LevelLoadingStrategy ls;
    private int width;
    private int height;

    @BeforeEach
    void setUp() {
        width = 10;
        height = 8;
        ls = new RandomLevelLoadingStrategy(width, height);
    }

    @Test
    void loadLevel() {
        assertNotNull(ls.loadLevel(null));
    }

    @Test
    void getPlayer() {
        ls.loadLevel(null);

        assertNotNull(ls.getPlayer());
        assertEntityIsInLevelBounds(ls.getPlayer());
    }

    @Test
    void getEntities() {
        ls.loadLevel(null);

        assertNotNull(ls.getEntities());
        ls.getEntities().forEach(this::assertEntityIsInLevelBounds);
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