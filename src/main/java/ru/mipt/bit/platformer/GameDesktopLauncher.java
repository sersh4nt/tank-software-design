package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    Playfield playfield;
    Player player;
    Obstacle obstacle;
    private Batch batch;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        playfield = new Playfield(batch);

        var playerTexture = new TextureRegion(new Texture("images/tank_blue.png"));
        var playerPosition = new GridPoint2(1, 1);
        player = new Player(playerTexture, playerPosition);

        var obstacleTexture = new TextureRegion(new Texture("images/greenTree.png"));
        var obstaclePosition = new GridPoint2(1, 3);
        obstacle = new Obstacle(obstacleTexture, obstaclePosition);
        playfield.addObstacle(obstacle);

//        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
//        blueTankTexture = new Texture("images/tank_blue.png");
//        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
//        playerGraphics = new TextureRegion(blueTankTexture);
//        playerRectangle = createBoundingRectangle(playerGraphics);
//        // set player initial position
//        playerDestinationCoordinates = new GridPoint2(1, 1);
//        playerCoordinates = new GridPoint2(playerDestinationCoordinates);
//        playerRotation = 0f;

//        greenTreeTexture = new Texture("images/greenTree.png");
//        treeObstacleGraphics = new TextureRegion(greenTreeTexture);
//        treeObstacleCoordinates = new GridPoint2(1, 3);
//        treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);
//        moveRectangleAtTileCenter(groundLayer, treeObstacleRectangle, treeObstacleCoordinates);
    }

    @Override
    public void render() {
        clearScreen();
        movePlayer();
        draw();
//
//        // calculate interpolated player screen coordinates
//        tileMovement.moveRectangleBetweenTileCenters(playerRectangle, playerCoordinates, playerDestinationCoordinates, playerMovementProgress);
//
//        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, MOVEMENT_SPEED);
//        if (isEqual(playerMovementProgress, 1f)) {
//            // record that the player has reached his/her destination
//            playerCoordinates.set(playerDestinationCoordinates);
//        }
//
//        // render each tile of the level
//        levelRenderer.render();
//
//        // start recording all drawing commands
//        batch.begin();
//
//        // render player
//        drawTextureRegionUnscaled(batch, playerGraphics, playerRectangle, playerRotation);
//
//        // render tree obstacle
//        drawTextureRegionUnscaled(batch, treeObstacleGraphics, treeObstacleRectangle, 0f);
//
//        // submit all drawing requests
//        batch.end();
    }

    private void draw() {
        playfield.renderField();

        batch.begin();
        player.draw(batch);
        obstacle.draw(batch);
        batch.end();
    }

    private void movePlayer() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        Direction newDirection = getNewDirection();
        player.move(newDirection, deltaTime, obstacle, playfield.getTileMovement());
    }

    private Direction getNewDirection() {
        var direction = player.getDirection();
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return Direction.RIGHT;
        }
        return direction;
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
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
        player.dispose();
        obstacle.dispose();
        playfield.dispose();
        batch.dispose();
    }
}
