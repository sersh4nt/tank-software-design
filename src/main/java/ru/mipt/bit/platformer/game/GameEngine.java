package ru.mipt.bit.platformer.game;

import ru.mipt.bit.platformer.game.entity.CollisionHandler;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameEngine {
    private static GameEngine gameEngine;
    private final CollisionHandler collisionHandler;
    private final GameListener listener;
    private final List<Entity> entities = new ArrayList<>();
    private final Set<Entity> entitiesToRemove = new HashSet<>();
    private final int width;
    private final int height;
    private Entity player = null;

    public GameEngine() {
        collisionHandler = new CollisionHandler();
        listener = null;
        width = 0;
        height = 0;
        gameEngine = this;
    }

    public GameEngine(int width, int height, GameListener listener) {
        collisionHandler = new CollisionHandler(width, height);
        this.listener = listener;
        this.width = width;
        this.height = height;
        gameEngine = this;
    }

    public static GameEngine getInstance() {
        return gameEngine;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity entity) {
        player = entity;
        addEntity(entity);
    }

    public List<Entity> getObstacles() {
        var result = new ArrayList<Entity>();
        entities.forEach(entity -> {
            if (entity instanceof Obstacle) result.add(entity);
        });
        return result;
    }

    public List<Entity> getEnemies() {
        var result = new ArrayList<Entity>();
        entities.forEach(entity -> {
            if (entity instanceof Tank && entity != player) result.add(entity);
        });
        return result;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Collidable collidable) {
            collisionHandler.addCollidable(collidable);
        }
        if (listener != null) {
            listener.onEntityAdded(entity);
        }
    }

    public void updateGameState(float deltaTime) {
        entitiesToRemove.clear();

        if (player != null) {
            player.updateState(deltaTime);
        }
        entities.forEach(entity -> entity.updateState(deltaTime));

        removeEntities();
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public void removeEntity(Entity entity) {
        entitiesToRemove.add(entity);
    }

    private void removeEntities() {
        entitiesToRemove.forEach(entity -> {
            entities.remove(entity);

            if (player == entity) {
                player = null;
            }

            if (entity instanceof Collidable collidable) {
                collisionHandler.removeMovable(collidable);
            }

            if (listener != null) {
                listener.onEntityRemoved(entity);
            }
        });
    }
}
