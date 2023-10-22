package ru.mipt.bit.platformer.game;

import ru.mipt.bit.platformer.game.entity.CollisionHandler;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final CollisionHandler collisionHandler;
    private final GameListener listener;
    private final List<Entity> obstacles = new ArrayList<>();
    private final List<Entity> enemies = new ArrayList<>();
    private final int width;
    private final int height;
    private Entity player = null;

    public GameEngine() {
        collisionHandler = new CollisionHandler();
        listener = null;
        width = 0;
        height = 0;
    }

    public GameEngine(int width, int height, GameListener listener) {
        collisionHandler = new CollisionHandler(width, height);
        this.listener = listener;
        this.width = width;
        this.height = height;
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
        return obstacles;
    }

    public List<Entity> getEnemies() {
        return enemies;
    }

    private void addEntity(Entity entity) {
        if (entity instanceof Collidable collidable) {
            collisionHandler.addCollidable(collidable);
        }
        if (listener != null) {
            listener.onEntityAdded(entity);
        }
    }

    public void addEnemy(Entity entity) {
        enemies.add(entity);
        addEntity(entity);
    }

    public void addObstacle(Entity entity) {
        obstacles.add(entity);
        addEntity(entity);
    }

    public void updateGameState(float deltaTime) {
        if (player != null) {
            player.updateState(deltaTime);
        }
        obstacles.forEach(obstacle -> obstacle.updateState(deltaTime));
        enemies.forEach(enemy -> enemy.updateState(deltaTime));
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }
}
