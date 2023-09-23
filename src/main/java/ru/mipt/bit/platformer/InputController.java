package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.util.Action;

import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Action> keyToAction = new HashMap<>();

    public void addMapping(int key, Action action) {
        keyToAction.put(key, action);
    }

    public Action getAction() {
        for (Integer key : keyToAction.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToAction.get(key);
            }
        }
        return null;
    }
}
