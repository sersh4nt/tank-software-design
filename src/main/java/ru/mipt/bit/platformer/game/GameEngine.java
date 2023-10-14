package ru.mipt.bit.platformer.game;

import ru.mipt.bit.platformer.game.entity.Collidable;
import ru.mipt.bit.platformer.game.entity.CollisionHandler;
import ru.mipt.bit.platformer.game.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final List<Entity> entities = new ArrayList<>();
    private final CollisionHandler collisionHandler;

    public GameEngine() {
        collisionHandler = new CollisionHandler();
    }

    public GameEngine(int width, int height) {
        collisionHandler = new CollisionHandler(width, height);
    }

    public void addEntity(Entity entity) {
        if (entity instanceof Collidable collidable) {
            collisionHandler.addCollidable(collidable);
        }
        entities.add(entity);
    }

    public void updateGameState(float deltaTime) {
        for (var entity : entities) {
            entity.updateState(deltaTime);
        }
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }
}
