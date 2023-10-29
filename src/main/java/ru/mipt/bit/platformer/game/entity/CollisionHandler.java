package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void removeMovable(Collidable collidable) {
        collidables.remove(collidable);
    }

    public Set<Collidable> getCollidablesAt(GridPoint2 point) {
        var result = new HashSet<Collidable>();
        if (isOutside(point)) return result;
        for (var collidable : collidables) {
            if (collidable.getDestinationCoordinates().equals(point)) result.add(collidable);
            if (collidable.getCoordinates().equals(point)) result.add(collidable);
        }
        return result;
    }

    public boolean isOutside(GridPoint2 point) {
        return point.x < 0 || point.y < 0 || point.x >= width || point.y >= height;
    }
}
