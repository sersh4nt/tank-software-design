package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollisionHandler implements GameListener {
    private final List<Collidable> collidables = new ArrayList<>();

    public Collidable checkCollisionAt(GridPoint2 destination, Collidable... whitelist) {
        for (var c : collidables) {
            if (Arrays.asList(whitelist).contains(c)) continue;

            if (c.getCoordinates().equals(destination) || c.getDestinationCoordinates().equals(destination)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void onEntityAdded(Entity entity) {
        if (entity instanceof Collidable collidable) {
            collidables.add(collidable);
        }
    }

    @Override
    public void onEntityRemoved(Entity entity) {
        if (entity instanceof Collidable collidable) {
            collidables.remove(collidable);
        }
    }
}
