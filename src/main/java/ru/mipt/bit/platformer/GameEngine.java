package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.EntityState;
import ru.mipt.bit.platformer.entity.Obstacle;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.util.Action;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final List<Obstacle> obstacles = new ArrayList<>();
    private final GameState gameState = new GameState();
    private Tank player;

    public void setPlayer(Tank player) {
        this.player = player;
        gameState.addEntity(player, new EntityState(player.getCoordinates(), player.getDestinationCoordinates(), player.getDirection(), player.getMovementProgress()));
    }

    public GameState updateGameState(float deltaTime, Action playerAction) {
        if (playerAction != null) {
            switch (playerAction) {
                case MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT -> handlePlayerMovement(playerAction);
                default -> {
                }
            }
        }

        updatePlayerState(deltaTime);

        return gameState;
    }

    private void updatePlayerState(float deltaTime) {
        var playerState = player.updateState(deltaTime);
        gameState.updateEntityState(player, playerState);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        gameState.addEntity(obstacle);
    }

    private void handlePlayerMovement(Action action) {
        var direction = action.getDirection();
        if (!player.isMoving()) {
            GridPoint2 targetCoordinates = direction.apply(player.getCoordinates());
            if (!hasObstaclesCollisions(targetCoordinates)) {
                player.moveTo(targetCoordinates);
            }
            player.rotate(direction);
        }
    }

    private boolean hasObstaclesCollisions(GridPoint2 targetCoordinates) {
        for (var obstacle : obstacles) {
            if (collides(obstacle.getCoordinates(), targetCoordinates)) return true;
        }
        return false;
    }

    private boolean collides(GridPoint2 object1, GridPoint2 object2) {
        return object1.equals(object2);
    }
}
