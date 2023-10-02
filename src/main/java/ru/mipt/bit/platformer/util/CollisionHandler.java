package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Collidable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionHandler {
    private final List<Collidable> collidables = new ArrayList<>();
    private final Set<GridPoint2> occupiedPoints = new HashSet<>();

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
        return occupiedPoints.contains(point);
    }
}
