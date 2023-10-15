package ru.mipt.bit.platformer.game.entity;

import com.badlogic.gdx.math.GridPoint2;

public interface Collidable {
    GridPoint2 getCoordinates();

    GridPoint2 getDestinationCoordinates();
}
