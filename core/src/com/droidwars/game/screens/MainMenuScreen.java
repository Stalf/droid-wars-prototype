package com.droidwars.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.droidwars.game.DroidWarsGame;
import com.droidwars.game.Resources;
import com.droidwars.game.background.BackgroundFXRenderer;


public class MainMenuScreen extends DefaultScreen implements InputProcessor {

    final BackgroundFXRenderer backgroundFX;

    Sprite titleSprite;

    Sprite startButtonSprite;

    SpriteBatch titleBatch;

    BoundingBox collisionStart = new BoundingBox();

    public MainMenuScreen(Game game) {
        super(game);
        Gdx.input.setInputProcessor(this);

        backgroundFX = new BackgroundFXRenderer();

        titleBatch = new SpriteBatch();
        titleBatch.getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);

        titleSprite = Resources.getInstance().title;
        titleSprite.setPosition(144, 320);

        startButtonSprite = Resources.getInstance().start;
        startButtonSprite.setPosition(336, 200);
        float[] vertices = startButtonSprite.getVertices();
        collisionStart.set(new Vector3(vertices[0], vertices[1], -10), new Vector3(vertices[10], vertices[11], 10));

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Очищаем экран и устанавливаем цвет фона черным
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backgroundFX.render();

        titleBatch.begin();

        titleSprite.draw(titleBatch);
        startButtonSprite.draw(titleBatch);

        titleBatch.end();
    }

    @Override
    public void resize(int width, int height) {

        super.resize(width, height);

        titleBatch.getProjectionMatrix().set(cam.combined);

    }

    @Override
    public void hide() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        Ray collisionRay = cam.getPickRay(x, y);

        if (Intersector.intersectRayBoundsFast(collisionRay, collisionStart)) {
            game.setScreen(new GameScreen(game));
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
