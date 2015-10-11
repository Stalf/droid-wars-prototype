package com.droidwars.game.ai;

/**
 * Пустой AI, который не выполняет никаких действий
 */
public class NullAI extends AbstractShipAI {

    @Override
    public void update() {
        // Do nothing
    }

    @Override
    public String getName() {
        return "Doing nothing";
    }
}
