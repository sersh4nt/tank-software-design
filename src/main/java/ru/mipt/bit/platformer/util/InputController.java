package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.entity.Entity;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Map.Entry<Entity, Command>> keyToEntityCommand = new HashMap<>();

    public void addMapping(int key, Entity entity, Command command) {
        keyToEntityCommand.put(key, new AbstractMap.SimpleEntry<>(entity, command));
    }

    public void applyCommands() {
        for (Integer key : keyToEntityCommand.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                var entity = keyToEntityCommand.get(key).getKey();
                var command = keyToEntityCommand.get(key).getValue();
                command.apply(entity);
            }
        }
    }
}
