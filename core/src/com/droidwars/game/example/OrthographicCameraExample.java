package com.droidwars.game.example;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class OrthographicCameraExample implements ApplicationListener {

    static final int WORLD_WIDTH = 1000;
    static final int WORLD_HEIGHT = 1000;

    private OrthographicCamera cam;
    private SpriteBatch batch;

    private Sprite mapSprite;
    private float rotationSpeed;

    private int x, y, z;

    @Override
    public void create() {
        rotationSpeed = 0.5f;

        mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(100, 100);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();

        x = 1;
        y = 1;
        z = 1;
    }

    @Override
    public void render() {
        handleInput();
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        mapSprite.draw(batch);
        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F1)) {
            x++;
            cam.lookAt(x, y, z);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F2)) {
            y++;
            cam.lookAt(x, y, z);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F3)) {
            z++;
            cam.lookAt(x, y, z);
        }

        /*cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);*/
    }

    @Override
    public void resize(int width, int height) {
//        cam.viewportWidth = width/100f;
        /*if (height > width ) {
            cam.viewportHeight = 100 * height / width;
            cam.viewportWidth = 100;
        } else {
            cam.viewportHeight = 100;
            cam.viewportWidth = 100 * width / height;
        }*/
        cam.viewportHeight = height;
        cam.viewportWidth = width;

        cam.update();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        mapSprite.getTexture().dispose();
        batch.dispose();
    }

    @Override
    public void pause() {
    }

}
