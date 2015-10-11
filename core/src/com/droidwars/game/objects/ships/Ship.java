package com.droidwars.game.objects.ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.ai.NullAI;
import com.droidwars.game.ai.ShipAI;
import com.droidwars.game.objects.GameObject;

/**
 * Абстрактный корабль
 */
public abstract class Ship extends GameObject {

    protected float turnSpeed = 1.0f;
    protected float accel = 0.0f;
    protected float hitPoints = 0;
    protected float maxHitPoints = 0;

    public ShipAI ai = new NullAI();

    public Ship(int id, Vector2 position, Vector2 facing) {
        super(id, position, facing);

        ai.init(this);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        if (!GameInstance.getInstance().gameOver) {
            ai.update();
        }

        if (hitPoints <= 0) {
            alive = false;

            GameInstance.getInstance().explosionParticles.addBigExplosion(this.randomPointOnShip());
            GameInstance.getInstance().explosionParticles.addBigExplosion(this.randomPointOnShip());
            GameInstance.getInstance().explosionParticles.addBigExplosion(this.randomPointOnShip());
            GameInstance.getInstance().explosionParticles.addBigExplosion(this.randomPointOnShip());
        }

    }

    /**
     * Выполняет выстрел
     */
    public abstract void shoot();

    /**
     * Поворачивает корабль в указанном направлении на максимально возможный за такт угол
     *
     * @param direction направление поворота: 1 - против часовой стрелки; -1 - по часовой стрелке
     */
    public void turn(int direction) {
        facing.rotate(direction * turnSpeed * delta).nor();
    }

    /**
     * Поворачивает корабль в направлении вектора. Учитывает максимальную скорость поворот корабля.
     *
     * @param direction требуемое направление корабля
     */
    public void turn(Vector2 direction) {
        float angle = facing.angle(direction);

        facing.rotate(Math.signum(angle) * Math.min(turnSpeed * delta, Math.abs(angle)) ).nor();
    }

    /**
     * Придает кораблю ускорение (в том направлении, в котором он в данный момент расположен)
     */
    public void thrust() {
        velocity.add(facing.x * accel * delta, facing.y * accel * delta);
    }

    /**
     * @return случайная точка на корабле
     */
    public Vector2 randomPointOnShip() {
        return new Vector2(collisionCenter.x + MathUtils.random(-this.getWidth() / 2, this.getWidth() / 2), collisionCenter.y
                + MathUtils.random(-this.getHeight() / 2, this.getHeight() / 2));
    }

    /**
     * @return жизнь корабля в процентах
     */
    public float healthPercentage() {
        return Math.max(hitPoints / maxHitPoints, 0);
    }

    public String health() {
        return String.format("HP: %.0f / %.0f", this.hitPoints, this.maxHitPoints);
    }

    /**
     * Уменьшает жизни корабля на переданную величину
     *
     * @param amount величина полученных повреждений
     */
    public void applyDamage(float amount) {
        hitPoints = Math.max(hitPoints - amount, 0);
    }

}
