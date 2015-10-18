package com.droidwars.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.droidwars.game.screens.GameScreen;
import com.droidwars.game.screens.MainMenuScreen;

public class DroidWarsGame extends Game {

    @Override
    public void create() {

        Gdx.app.setLogLevel(Application.LOG_INFO);

//        setScreen(new MainMenuScreen(this));
        setScreen(new GameScreen(this));
    }
}
