package com.droidwars.game.ai;

import com.badlogic.gdx.math.MathUtils;

/**
 * Просто стоит на месте и стреляет
 */
public class ShootOnlyAI extends AbstractShipAI {

    public final float shootProbability = 0.01f;

    @Override
    public void update() {
        if (MathUtils.randomBoolean(shootProbability)) {
            ship.shoot();
        }
    }

    @Override
    public String getName() {
        return "Fires in random moments only";
    }
}
