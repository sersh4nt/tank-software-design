package ru.mipt.bit.platformer.game.commands;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.Shootable;

public class ShootCommand implements Command {
    /*
    adapter
     */
    @Override
    public void apply(Entity entity) {
        if (entity instanceof Shootable shootable) {
            shootable.shoot();
        }
    }
}
