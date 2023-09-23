package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Obstacle;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.graphics.GameGraphics;
import ru.mipt.bit.platformer.graphics.GdxGameGraphicsImpl;
import ru.mipt.bit.platformer.util.Action;
import ru.mipt.bit.platformer.util.Direction;

import static com.badlogic.gdx.Input.Keys.*;

public class GameDesktopLauncher implements ApplicationListener {
    private GameGraphics gameGraphics;
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
        // later we will inject both engine and graphics, as well as players and obstacles

        gameEngine = new GameEngine();
        var player = new Tank(new GridPoint2(1, 1), Direction.RIGHT);
        var obstacle = new Obstacle(new GridPoint2(1, 3));
        gameEngine.setPlayer(player);
        gameEngine.addObstacle(obstacle);

        gameGraphics = new GdxGameGraphicsImpl();
        gameGraphics.loadLevel("level.tmx");
        var playerTexture = gameGraphics.createTexture("images/tank_blue.png");
        var obstacleTexture = gameGraphics.createTexture("images/greenTree.png");
        gameGraphics.addEntityTexture(player, playerTexture);
        gameGraphics.addEntityTexture(obstacle, obstacleTexture);

        initKeyMappings();
    }

    private void initKeyMappings() {
        inputController = new InputController();
        inputController.addMapping(UP, Action.MOVE_UP);
        inputController.addMapping(W, Action.MOVE_UP);
        inputController.addMapping(LEFT, Action.MOVE_LEFT);
        inputController.addMapping(A, Action.MOVE_LEFT);
        inputController.addMapping(DOWN, Action.MOVE_DOWN);
        inputController.addMapping(S, Action.MOVE_DOWN);
        inputController.addMapping(RIGHT, Action.MOVE_RIGHT);
        inputController.addMapping(D, Action.MOVE_RIGHT);
    }

    @Override
    public void render() {
        gameGraphics.clearScreen();
        float deltaTime = gameGraphics.getDeltaTime();
        Action action = inputController.getAction();
        var gameState = gameEngine.updateGameState(deltaTime, action);
        gameGraphics.renderGame(gameState);
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
        gameGraphics.dispose();
    }
}