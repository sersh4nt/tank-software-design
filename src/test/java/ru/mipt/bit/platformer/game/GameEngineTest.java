package ru.mipt.bit.platformer.game;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testEntityAddedListenerCalled() {
        var listener = mock(GameListener.class);
        var entity = mock(Entity.class);
        var gameEngine = new GameEngine(0, 0, listener);

        gameEngine.addEntity(entity);

        verify(listener, times(1)).onEntityAdded(entity);
    }

    @Test
    public void testGetEnemiesReturnTanks() {
        var obstacle = mock(Obstacle.class);
        var tank = mock(Tank.class);
        var gameEngine = new GameEngine();

        gameEngine.addEntity(obstacle);
        gameEngine.addEntity(tank);

        var enemies = gameEngine.getEnemies();
        assertEquals(1, enemies.size());
        assertEquals(tank, enemies.get(0));
    }
}