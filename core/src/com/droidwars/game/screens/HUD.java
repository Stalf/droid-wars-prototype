package com.droidwars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.ships.Ship;

public class HUD {

    private BitmapFont font;
    private SpriteBatch HUDBatch;

    private int width;
    private int height;

    public HUD() {

        HUDBatch = new SpriteBatch();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;

        HUDBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    public void render() {

        HUDBatch.begin();
        font.setColor(Color.WHITE);
        font.draw(HUDBatch, "fps: " + Gdx.graphics.getFramesPerSecond(), 10, 20);

        font.setColor(Color.SKY);
        Ship ship1 = GameInstance.getInstance().ships.get(0);
        font.draw(HUDBatch, ship1.ai.getName(), 10, height - 10);
        font.draw(HUDBatch, String.format("speed: %.2f", ship1.velocity.len()), 10, height - 25);
        font.draw(HUDBatch, ship1.health(), 10, height - 40);

        font.setColor(Color.PINK);
        Ship ship2 = GameInstance.getInstance().ships.get(1);
        font.draw(HUDBatch, ship2.ai.getName(), width - 200, height - 10);
        font.draw(HUDBatch, String.format("speed: %.2f", ship2.velocity.len()), width - 200, height - 25);
        font.draw(HUDBatch, ship2.health(), width - 200, height - 40);
        HUDBatch.end();

    }

    public void renderGameOver() {
        HUDBatch.begin();
        font.setColor(Color.GOLD);
        font.draw(HUDBatch, "GAME OVER", 350, 350);
        if (GameInstance.getInstance().ships.get(0).alive) {
            font.setColor(Color.SKY);
            font.draw(HUDBatch, "Blue player wins", 340, 300);
        } else {
            font.setColor(Color.PINK);
            font.draw(HUDBatch, "Red player wins", 340, 300);
        }
        HUDBatch.end();
    }

}
