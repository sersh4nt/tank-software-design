package ru.mipt.bit.platformer.game.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomEntityControllerTest {

    @Test
    void getCommand() {
        var ec = new RandomEntityController();

        assertNotNull(ec.getCommand());
    }
}