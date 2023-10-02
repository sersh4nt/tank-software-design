package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.util.CollisionHandler;
import ru.mipt.bit.platformer.util.Direction;

public interface Movable {
    void addCollisionHandler(CollisionHandler collisionHandler);

    void moveTo(Direction direction);
}
