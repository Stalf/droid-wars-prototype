package com.droidwars.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.droidwars.game.GameInstance;
import com.droidwars.game.background.BackgroundFXRenderer;
import com.droidwars.game.utils.Constants;

public class GameScreen extends DefaultScreen implements InputProcessor {

    private BackgroundFXRenderer backgroundFX;

    private SpriteBatch gameBatch;

    private HUD hud;

    public GameScreen(Game game) {
        super(game);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);

        backgroundFX = new BackgroundFXRenderer();

        hud = new HUD();

        gameBatch = new SpriteBatch();

        cam = new OrthographicCamera(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        GameInstance.getInstance().resetGame();

        GameInstance.getInstance().startNewGame();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = Constants.MAP_HEIGHT;
        cam.viewportWidth = Constants.MAP_WIDTH;

        cam.update();

        hud.resize(width, height);
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

        cam.update();
        gameBatch.setProjectionMatrix(cam.combined);

        gameBatch.begin();

        GameInstance.getInstance().renderAll(gameBatch);

        gameBatch.end();

        hud.render();

        if (GameInstance.getInstance().gameOver) {
            hud.renderGameOver();
        }

    }

    @Override
    public void hide() {

    }

    @Override
    public boolean keyDown(int keycode) {

        if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
        }

        if (keycode == Input.Keys.SPACE) {
//            GameInstance.getInstance().explosionParticles.addBigExplosion(GameInstance.getInstance().ships.get(0).position);
            GameInstance.getInstance().ships.get(0).applyDamage(2000);
        }

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
