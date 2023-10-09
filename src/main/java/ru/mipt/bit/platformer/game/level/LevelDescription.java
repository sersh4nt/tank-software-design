package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

public class LevelDescription {
    public final List<GridPoint2> obstacles;
    public final GridPoint2 playerPosition;

    public LevelDescription(List<GridPoint2> obstacles, GridPoint2 playerPosition) {
        this.obstacles = obstacles;
        this.playerPosition = playerPosition;
    }
}
