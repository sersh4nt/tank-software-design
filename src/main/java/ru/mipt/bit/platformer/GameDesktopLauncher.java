package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.commands.MoveCommand;
import ru.mipt.bit.platformer.entity.Entity;
import ru.mipt.bit.platformer.entity.Obstacle;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.graphics.GdxGameGraphics;
import ru.mipt.bit.platformer.graphics.GdxTankImpl;
import ru.mipt.bit.platformer.graphics.GdxTexture;
import ru.mipt.bit.platformer.graphics.GdxTreeImpl;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.GameEngine;
import ru.mipt.bit.platformer.util.InputController;

import static com.badlogic.gdx.Input.Keys.*;

public class GameDesktopLauncher implements ApplicationListener {
    private GdxGameGraphics gdxGameGraphics;
    private GameEngine gameEngine;
    private InputController inputController;


    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        gameEngine = new GameEngine();
        gdxGameGraphics = new GdxGameGraphics("level.tmx");

        createPlayer();
        createObstacles();
    }

    private void createObstacles() {
        var obstacle = new Obstacle(new GridPoint2(1, 3));
        gameEngine.addEntity(obstacle);
        var obstacleTexture = new GdxTexture("images/greenTree.png");
        gdxGameGraphics.addRenderable(new GdxTreeImpl(obstacle, obstacleTexture, gdxGameGraphics.getGroundLayer()));
    }

    private void createPlayer() {
        var player = new Tank(new GridPoint2(1, 1), Direction.RIGHT, 0.4f);
        gameEngine.addEntity(player);
        var playerTexture = new GdxTexture("images/tank_blue.png");
        gdxGameGraphics.addRenderable(new GdxTankImpl(player, playerTexture, gdxGameGraphics.getTileMovement()));
        initKeyMappings(player);
    }

    private void initKeyMappings(Entity player) {
        inputController = new InputController();
        inputController.addMapping(UP, player, new MoveCommand(Direction.UP));
        inputController.addMapping(W, player, new MoveCommand(Direction.UP));
        inputController.addMapping(LEFT, player, new MoveCommand(Direction.LEFT));
        inputController.addMapping(A, player, new MoveCommand(Direction.LEFT));
        inputController.addMapping(DOWN, player, new MoveCommand(Direction.DOWN));
        inputController.addMapping(S, player, new MoveCommand(Direction.DOWN));
        inputController.addMapping(RIGHT, player, new MoveCommand(Direction.RIGHT));
        inputController.addMapping(D, player, new MoveCommand(Direction.RIGHT));
    }

    @Override
    public void render() {
        float deltaTime = gdxGameGraphics.getDeltaTime();
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