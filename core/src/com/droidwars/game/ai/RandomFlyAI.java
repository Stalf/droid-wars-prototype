package com.droidwars.game.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.utils.GameUtils;

/**
 * AI случайным образом выбирает направление полета, поворачивает корабль и набирает скорость.
 * После достижения максимальной скорости - выбирает новое направление.
 */
public class RandomFlyAI extends AbstractShipAI {

    // Направление, в которое необходимо повернуть корабль
    private Vector2 targetDirection = new Vector2();

    // Целевая максимальная скорость
    private final int targetVelocity = 50;
    // Целевая максимальная скорость в квадрате
    private final int targetVelocity2 = targetVelocity * targetVelocity;

    @Override
    public void init(Ship ship) {
        super.init(ship);

        targetDirection.set(ship.facing);
    }

    @Override
    public void update() {

        // Угол между требуемым направлением на цель и кораблем
        float angle = MathUtils.round(ship.facing.angle(targetDirection));

        Gdx.app.debug("ship" + String.valueOf(ship.id), String.format("angle: %.2f", angle));
        Gdx.app.debug("ship" + String.valueOf(ship.id), String.format("velocity: %.2f", ship.velocity.len()));
        Gdx.app.debug("ship" + String.valueOf(ship.id), String.format("velocity projection: %.2f", ship.velocity.dot(targetDirection)));
        // Проверяем смотрит ли корабль в требуемом направлении
        if (angle == 0) {
            // Проверяем скорость в заданном направлении
            if (ship.velocity.dot(targetDirection) >= targetVelocity) {
                Gdx.app.debug("ship" + String.valueOf(ship.id), "new direction");
                // Если необходимая скорость достигнута - генерируем новое случайное направление
                targetDirection.set(GameUtils.generateRandomDirection());
            } else {
                Gdx.app.debug("ship" + String.valueOf(ship.id), "thrust");
                // иначе - даем газу
                ship.thrust();
            }
        } else {
            Gdx.app.debug("ship"+String.valueOf(ship.id), "turn");
            // Если нет - поворачиваем корабль
            ship.turn(targetDirection);
        }
    }

    @Override
    public String getName() {
        return "Randomly flying";
    }


}
