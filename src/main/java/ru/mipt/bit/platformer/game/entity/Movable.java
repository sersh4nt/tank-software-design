package ru.mipt.bit.platformer.game.entity;

import ru.mipt.bit.platformer.game.Direction;

public interface Movable {
    void moveTo(Direction direction);
}
