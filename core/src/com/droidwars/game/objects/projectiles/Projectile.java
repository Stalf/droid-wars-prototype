package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.utils.GameUtils;

/**
 * Абстрактный класс снаряда, который может наносить урон
 */
public abstract class Projectile extends GameObject {

    /**
     * Величина урона
     */
    public float damage = 0;

    /**
     * Максимальное время жизни
     */
    public float maxAliveTime = 0;

    /**
     * Максимальное пройденное расстояние
     */
    public float maxTravelDistance = 0;

    /**
     * Максимальная скорость перемещения
     */
    public float maxVelocity = 0;

    /**
     * Корабль, который произвел выстрел
     */
    public Ship owner;

    public Projectile(Ship ship) {
        super(ship.id, new Vector2(ship.collisionCenter).add(new Vector2(ship.facing).scl(ship.getWidth(), ship.getHeight())), ship.facing);

        this.owner = ship;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        if ((maxAliveTime > 0) && (aliveTime > maxAliveTime)) {
            this.destroy();
        }

        if ((maxTravelDistance > 0) && (travelDistance > maxTravelDistance)) {
            this.destroy();
        }

    }

    @Override
    public void destroy() {

        GameInstance.getInstance().explosionParticles.addTinyExplosion(this.position);

        super.destroy();
    }
}
