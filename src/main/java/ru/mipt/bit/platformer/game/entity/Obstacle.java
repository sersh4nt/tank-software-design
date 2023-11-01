package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.Livable;

public record Obstacle(GridPoint2 coordinates, GameEngine gameEngine) implements Entity, Collidable, Livable {

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return coordinates;
    }

    @Override
    public void updateState(float deltaTime) {
    }

    @Override
    public void damage(float damage) {
        gameEngine.removeEntity(this);
    }
}
