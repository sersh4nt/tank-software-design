package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
    private final List<Collidable> collidables = new ArrayList<>();
    private int width = 0;
    private int height = 0;

    public CollisionHandler() {
    }

    public CollisionHandler(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    public void removeCollidable(Collidable collidable) {
        collidables.remove(collidable);
    }

    public Collidable checkCollisionAt(Collidable collidable, GridPoint2 destination) {
        for (var c : collidables) {
            if (c == collidable) continue;

            if (c.getCoordinates().equals(destination) || c.getDestinationCoordinates().equals(destination)) {
                return c;
            }
        }
        return null;
    }

    public boolean isOutside(GridPoint2 point) {
        return point.x < 0 || point.y < 0 || point.x >= width || point.y >= height;
    }
}
