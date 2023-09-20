package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Direction> keyToDirection = new HashMap<>();

    public void addMapping(int key, Direction direction) {
        keyToDirection.put(key, direction);
    }

    public Direction getDirection() {
        for (Integer key : keyToDirection.keySet()) {
            if (Gdx.input.isKeyJustPressed(key)) {
                return keyToDirection.get(key);
            }
        }
        return null;
    }
}
