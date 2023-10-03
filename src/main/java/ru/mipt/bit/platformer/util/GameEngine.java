package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.entity.Collidable;
import ru.mipt.bit.platformer.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final List<Entity> entities = new ArrayList<>();
    private final CollisionHandler collisionHandler = new CollisionHandler();

    public void addEntity(Entity entity) {
        if (entity instanceof Collidable) {
            collisionHandler.addCollidable((Collidable) entity);
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
