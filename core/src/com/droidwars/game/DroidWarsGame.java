package com.droidwars.game;

import com.badlogic.gdx.Game;
import com.droidwars.game.screens.GameScreen;
import com.droidwars.game.screens.MainMenuScreen;

public class DroidWarsGame extends Game {

    @Override
    public void create() {
//        setScreen(new MainMenuScreen(this));
        setScreen(new GameScreen(this));
    }
}
