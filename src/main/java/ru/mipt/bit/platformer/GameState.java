package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.entity.Entity;
import ru.mipt.bit.platformer.entity.EntityState;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private final Map<Entity, EntityState> entityStates;

    public GameState() {
        this.entityStates = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        var entityState = new EntityState(entity.getCoordinates(), entity.getCoordinates(), entity.getDirection(), 1f);
        if (entityStates.containsKey(entity)) {
            updateEntityState(entity, entityState);
            return;
        }
        entityStates.put(entity, entityState);
    }

    public void addEntity(Entity entity, EntityState entityState) {
        if (entityStates.containsKey(entity)) {
            updateEntityState(entity, entityState);
            return;
        }
        entityStates.put(entity, entityState);
    }

    public void updateEntityState(Entity entity, EntityState entityState) {
        if (!entityStates.containsKey(entity)) {
            addEntity(entity, entityState);
            return;
        }
        entityStates.replace(entity, entityState);
    }

    public Map<Entity, EntityState> getEntityStates() {
        return entityStates;
    }
}
