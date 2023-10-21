package ru.mipt.bit.platformer.game;

import ru.mipt.bit.platformer.game.entity.CollisionHandler;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final List<Entity> entities = new ArrayList<>();
    private final CollisionHandler collisionHandler;
    private final GameListener listener;

    public GameEngine() {
        collisionHandler = new CollisionHandler();
        listener = null;
    }

    public GameEngine(int width, int height, GameListener listener) {
        collisionHandler = new CollisionHandler(width, height);
        this.listener = listener;
    }

    public void addEntity(Entity entity) {
        if (entity instanceof Collidable collidable) {
            collisionHandler.addCollidable(collidable);
        }
        entities.add(entity);
        if (listener != null) {
            listener.onEntityAdded(entity);
        }
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
