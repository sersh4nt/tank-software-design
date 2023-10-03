package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.util.Direction;

public interface Movable {
    void moveTo(Direction direction);
}
