package ru.mipt.bit.platformer.util;

public enum Action {
    MOVE_LEFT(Direction.LEFT), MOVE_RIGHT(Direction.RIGHT), MOVE_UP(Direction.UP), MOVE_DOWN(Direction.DOWN);

    private final Direction direction;

    Action(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
