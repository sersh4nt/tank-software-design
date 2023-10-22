package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionHandler {
    private final List<Collidable> collidables = new ArrayList<>();
    private final Set<GridPoint2> occupiedPoints = new HashSet<>();
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

    private void updateField() {
        occupiedPoints.clear();
        for (var collidable : collidables) {
            occupiedPoints.add(collidable.getCoordinates());
            occupiedPoints.add(collidable.getDestinationCoordinates());
        }
    }

    public boolean isOccupied(GridPoint2 point) {
        updateField();
        if (isOutside(point)) {
            return true;
        }
        return occupiedPoints.contains(point);
    }

    private boolean isOutside(GridPoint2 point) {
        return point.x < 0 || point.y < 0 || point.x >= width || point.y >= height;
    }
}
