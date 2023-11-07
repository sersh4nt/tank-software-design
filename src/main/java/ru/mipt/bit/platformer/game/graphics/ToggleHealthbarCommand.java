package ru.mipt.bit.platformer.game.graphics;

import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Entity;

public class ToggleHealthbarCommand implements Command {
    /*
    adapter
     */
    private final GameGraphics graphics;
    private final float delaySeconds;
    private long lastUsedAt = System.currentTimeMillis();

    public ToggleHealthbarCommand(GameGraphics graphics, float delaySeconds) {
        this.graphics = graphics;
        this.delaySeconds = delaySeconds;
    }

    @Override
    public void apply(Entity entity) {
        var currentTime = System.currentTimeMillis();
        if (currentTime - lastUsedAt >= delaySeconds * 1000) {
            lastUsedAt = currentTime;
            graphics.toggleRenderHealthbar();
        }
    }
}
