package ru.mipt.bit.platformer.game;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.entity.Entity;

import static org.mockito.Mockito.*;

class GameEngineTest {
    @Test
    public void testAddedEntityUpdateMethodCalledWhenGameUpdates() {
        float deltaTime = 1f;

        Entity entity = mock(Entity.class);
        var engine = new GameEngine();

        engine.addEntity(entity);
        engine.updateGameState(deltaTime);

        verify(entity, times(1)).updateState(deltaTime);
    }

    @Test
    public void testNotAddedEntityUpdateMethodNotCalledWhenGameUpdates() {
        float deltaTime = 1f;

        Entity entity = mock(Entity.class);
        var engine = new GameEngine();

        engine.addEntity(entity);

        verify(entity, times(0)).updateState(deltaTime);
    }
}