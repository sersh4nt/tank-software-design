package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.Livable;

public class Obstacle implements Entity, Collidable, Livable {
    private final GridPoint2 coordinates;
    private final GameEngine gameEngine;

    public Obstacle(GridPoint2 coordinates, GameEngine gameEngine) {
        this.coordinates = coordinates;
        this.gameEngine = gameEngine;
    }

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
