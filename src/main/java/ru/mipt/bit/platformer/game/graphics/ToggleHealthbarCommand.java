package ru.mipt.bit.platformer.game.graphics;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Entity;

public class ToggleHealthbarCommand implements Command {
    /*
    adapter
     */
    private final GameGraphics graphics;

    public ToggleHealthbarCommand(GameGraphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void apply(Entity entity) {
        graphics.toggleRenderHealthbar();
    }
}
