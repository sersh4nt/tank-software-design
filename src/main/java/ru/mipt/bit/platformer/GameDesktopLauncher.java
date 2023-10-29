package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.GameListener;
import ru.mipt.bit.platformer.game.commands.EntityController;
import ru.mipt.bit.platformer.game.commands.MoveCommand;
import ru.mipt.bit.platformer.game.commands.RandomEntityController;
import ru.mipt.bit.platformer.game.commands.ShootCommand;
import ru.mipt.bit.platformer.game.entity.InputController;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.graphics.GdxGameGraphics;
import ru.mipt.bit.platformer.game.graphics.GdxGraphicsListener;
import ru.mipt.bit.platformer.game.level.FileLevelGenerator;
import ru.mipt.bit.platformer.game.level.LevelGenerator;
import ru.mipt.bit.platformer.game.listener.CompositeListener;

import java.util.Random;

import static com.badlogic.gdx.Input.Keys.*;

public class GameDesktopLauncher implements ApplicationListener {
    private final InputController inputController = new InputController();
    private GdxGameGraphics gdxGameGraphics;
    private GameEngine gameEngine;
    private EntityController entityController;


    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        gdxGameGraphics = new GdxGameGraphics("level.tmx");

        entityController = new RandomEntityController();
//        loadingStrategy = new RandomLevelLoadingStrategy(10, 8);
        LevelGenerator loadingStrategy = new FileLevelGenerator("map.txt");
        var listener = setupListener();
        gameEngine = loadingStrategy.loadLevel(listener);

        createEnemies();
        initKeyMappings();
    }

    private GameListener setupListener() {
        var listener = new CompositeListener();
        listener.addListener(new GdxGraphicsListener(gdxGameGraphics));
        return listener;
    }

    private void createEnemies() {
        var mx = new Random().nextInt(1, 5);
        for (int i = 0; i < mx; i++) {
            var tank = new Tank(new GridPoint2(0, i + 2), Direction.RIGHT, 1f, 100f, 1000f, gameEngine.getCollisionHandler());
            gameEngine.addEntity(tank);
        }
    }

    private void initKeyMappings() {
        var player = gameEngine.getPlayer();
        inputController.addMapping(UP, player, new MoveCommand(Direction.UP));
        inputController.addMapping(W, player, new MoveCommand(Direction.UP));
        inputController.addMapping(LEFT, player, new MoveCommand(Direction.LEFT));
        inputController.addMapping(A, player, new MoveCommand(Direction.LEFT));
        inputController.addMapping(DOWN, player, new MoveCommand(Direction.DOWN));
        inputController.addMapping(S, player, new MoveCommand(Direction.DOWN));
        inputController.addMapping(RIGHT, player, new MoveCommand(Direction.RIGHT));
        inputController.addMapping(D, player, new MoveCommand(Direction.RIGHT));
        inputController.addMapping(SPACE, player, new ShootCommand());
    }

    @Override
    public void render() {
        float deltaTime = gdxGameGraphics.getDeltaTime();

        var commands = entityController.getCommands(gameEngine);

        commands.forEach((k, v) -> v.apply(k));
        inputController.applyCommands();

        gameEngine.updateGameState(deltaTime);

        gdxGameGraphics.render();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        gdxGameGraphics.dispose();
    }
}