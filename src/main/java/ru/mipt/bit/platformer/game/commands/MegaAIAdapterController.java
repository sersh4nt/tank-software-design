package ru.mipt.bit.platformer.game.commands;

import org.awesome.ai.AI;
import org.awesome.ai.Action;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.game.Command;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.entity.Tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MegaAIAdapterController implements EntityController {
    private final AIAdapter adapter = new AIAdapter();
    private final AI ai;

    public MegaAIAdapterController(AI ai) {
        this.ai = ai;
    }

    @Override
    public Map<Entity, Command> getCommands(GameEngine engine) {
        var gameState = adapter.getGameState(engine);
        var result = new HashMap<Entity, Command>();

        ai.recommend(gameState).forEach(
                recommendation -> result.put(
                        (Entity) recommendation.getActor().getSource(),
                        adapter.getCommand(recommendation.getAction())
                )
        );

        return result;
    }

    public static class AIAdapter {
        public Command getCommand(Action action) {
            switch (action) {
                case Shoot -> {
                    return new ShootCommand();
                }
                case MoveEast -> {
                    return new MoveCommand(Direction.RIGHT);
                }
                case MoveNorth -> {
                    return new MoveCommand(Direction.UP);
                }
                case MoveSouth -> {
                    return new MoveCommand(Direction.DOWN);
                }
                case MoveWest -> {
                    return new MoveCommand(Direction.LEFT);
                }
            }
            return null;
        }

        public GameState getGameState(GameEngine engine) {
            var player = (Tank) engine.getPlayer();
            var enemies = engine.getEnemies();
            var obstacles = engine.getObstacles();
            return new GameState.GameStateBuilder()
                    .levelHeight(engine.getHeight())
                    .levelWidth(engine.getWidth())
                    .player(getPlayer(player))
                    .bots(getBots(enemies))
                    .obstacles(getObstacles(obstacles))
                    .build();
        }

        private Player getPlayer(Tank player) {
            return new Player.PlayerBuilder()
                    .source(player)
                    .x(player.getCoordinates().x)
                    .y(player.getCoordinates().y)
                    .destX(player.getDestinationCoordinates().x)
                    .destY(player.getDestinationCoordinates().y)
                    .orientation(getOrientation(player.getDirection()))
                    .build();
        }

        private List<Obstacle> getObstacles(List<Entity> obstacles) {
            var result = new ArrayList<Obstacle>();
            obstacles.forEach(entity -> {
                var obstacle = (ru.mipt.bit.platformer.game.entity.Obstacle) entity;
                result.add(new Obstacle(obstacle.getCoordinates().x, obstacle.getCoordinates().y));
            });
            return result;
        }

        private List<Bot> getBots(List<Entity> enemies) {
            var result = new ArrayList<Bot>();
            enemies.forEach(entity -> {
                var tank = (Tank) entity;
                result.add(new Bot.BotBuilder()
                        .source(tank)
                        .x(tank.getCoordinates().x)
                        .y(tank.getCoordinates().y)
                        .destX(tank.getDestinationCoordinates().x)
                        .destY(tank.getDestinationCoordinates().y)
                        .orientation(getOrientation(tank.getDirection()))
                        .build()
                );
            });
            return result;
        }

        private Orientation getOrientation(Direction direction) {
            switch (direction) {
                case UP -> {
                    return Orientation.N;
                }
                case DOWN -> {
                    return Orientation.S;
                }
                case LEFT -> {
                    return Orientation.W;
                }
                case RIGHT -> {
                    return Orientation.E;
                }
            }
            return null;
        }
    }
}
