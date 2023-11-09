package ru.mipt.bit.platformer.game.commands;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.GameEngine;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class RandomEntityControllerTest {

    @Test
    void getCommand() {
        var ec = new RandomEntityController();
        var engine = mock(GameEngine.class);

        assertNotNull(ec.getCommands(engine));
    }
}