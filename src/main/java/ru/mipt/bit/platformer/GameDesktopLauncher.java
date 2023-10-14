package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameEngine;
import ru.mipt.bit.platformer.game.commands.MoveCommand;
import ru.mipt.bit.platformer.game.entity.Entity;
import ru.mipt.bit.platformer.game.entity.InputController;
import ru.mipt.bit.platformer.game.entity.Obstacle;
import ru.mipt.bit.platformer.game.entity.Tank;
import ru.mipt.bit.platformer.game.graphics.GdxGameGraphics;
import ru.mipt.bit.platformer.game.graphics.GdxTankImpl;
import ru.mipt.bit.platformer.game.graphics.GdxTreeImpl;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.level.FileLevelLoadingStrategy;
import ru.mipt.bit.platformer.game.level.LevelLoadingStrategy;

import static com.badlogic.gdx.Input.Keys.*;

public class GameDesktopLauncher implements ApplicationListener {
    private GdxGameGraphics gdxGameGraphics;
    private GameEngine gameEngine;
    private InputController inputController;
    private LevelLoadingStrategy loadingStrategy;


    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
//        loadingStrategy = new RandomLevelLoadingStrategy(10, 8);
        loadingStrategy = new FileLevelLoadingStrategy("map.txt");

        gameEngine = loadingStrategy.loadLevel();

        gdxGameGraphics = new GdxGameGraphics("level.tmx");
        bindGraphics();

        initKeyMappings(loadingStrategy.getPlayer());
    }

    private void bindGraphics() {
        for (var entity : loadingStrategy.getEntities()) {
            var renderable = getRenderableFromEntity(entity);
            gdxGameGraphics.addRenderable(renderable);
        }
    }

    private Renderable getRenderableFromEntity(Entity entity) {
        if (entity instanceof Obstacle obstacle) {
            return new GdxTreeImpl(obstacle, gdxGameGraphics.getGroundLayer());
        }
        if (entity instanceof Tank tank) {
            return new GdxTankImpl(tank, gdxGameGraphics.getTileMovement());
        }
        return null;
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