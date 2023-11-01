package ru.mipt.bit.platformer.game.listener;

import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameListener;

import java.util.ArrayList;
import java.util.List;

public class CompositeListener implements GameListener {
    /*
    adapter
     */
    private final List<GameListener> listeners = new ArrayList<>();

    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onEntityAdded(Entity entity) {
        listeners.forEach(listener -> listener.onEntityAdded(entity));
    }

    @Override
    public void onEntityRemoved(Entity entity) {
        listeners.forEach(listener -> listener.onEntityRemoved(entity));
    }
}
