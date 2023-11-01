package ru.mipt.bit.platformer.game;

import com.badlogic.gdx.math.GridPoint2;

public interface Collidable {
    /*
    port
     */
    GridPoint2 getCoordinates();

    GridPoint2 getDestinationCoordinates();
}
