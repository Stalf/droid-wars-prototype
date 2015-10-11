package com.droidwars.game.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.objects.ships.Ship;

/**
 * Летает случайным образом, как {@link RandomFlyAI}. Выполняет выстрел в случайный момент времени. Вероятность выстрела задается параметром {@link RandomShootAI#shootProbability}
 */
public class RandomShootAI extends RandomFlyAI {

    public final float shootProbability = 0.01f;

    @Override
    public void init(Ship ship) {
        super.init(ship);
    }

    @Override
    public void update() {
        super.update();

        if (MathUtils.randomBoolean(shootProbability)) {
            ship.shoot();
        }
    }

    @Override
    public String getName() {
        return "Randomly flying and shooting";
    }
}
