package com.droidwars.game.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.projectiles.Missile;
import com.droidwars.game.objects.projectiles.Projectile;
import com.droidwars.game.objects.ships.Frigate;
import com.droidwars.game.objects.ships.Ship;

public class GameUtils {

    /**
     * Генерирует случайный нормализованный вектор направления
     *
     * @return случайный вектор направления
     */
    public static Vector2 generateRandomDirection() {
        return new Vector2(MathUtils.random(-1f, 1f), MathUtils.random(-1f, 1f)).nor();
    }

    /**
     * Генерирует случайный вектор положения объекта в игровом мире
     *
     * @return случайный вектор положения в мире
     */
    public static Vector2 generateRandomPosition() {
        return new Vector2(MathUtils.random(0, Constants.MAP_WIDTH), MathUtils.random(0, Constants.MAP_HEIGHT));
    }

    /**
     * Проверяет все снаряды и все корабли на попадание
     */
    //TODO функция взята из примера. Возможно нужно отрефакторить
    public static void collisionCheck() {

        for (int i = 0; i < GameInstance.getInstance().projectiles.size; i++) {
            Projectile projectile = GameInstance.getInstance().projectiles.get(i);
            if (projectile.alive) {
                for (int n = 0; n < GameInstance.getInstance().ships.size; n++) {
                    Ship ship = GameInstance.getInstance().ships.get(n);
                    collisionCheck(projectile, ship);
                }
            }

        }
    }

    /**
     * Проверяет попадание снаряда в корабль
     *
     * @param projectile снаряд
     * @param ship       корабль
     */
    //TODO функция взята из примера. Возможно нужно отрефакторить
    private static void collisionCheck(Projectile projectile, Ship ship) {
        if ((ship.id != projectile.id) && (ship.alive)) {

            for (int i = 0; i < ship.collisionPoints.size; ++i) {
                if (Intersector.isPointInPolygon(projectile.collisionPoints, ship.collisionPoints.get(i))) {
                    ship.applyDamage(projectile.damage);
                    GameUtils.bulletHit(ship, projectile);
                    projectile.alive = false;
                    return;
                }
            }

            for (int i = 0; i < projectile.collisionPoints.size; ++i) {
                if (Intersector.isPointInPolygon(ship.collisionPoints, projectile.collisionPoints.get(i))) {
                    ship.applyDamage(projectile.damage);
                    GameUtils.bulletHit(ship, projectile);
                    projectile.alive = false;
                    return;
                }
            }
        }
    }

    /**
     * Функция реализует отображение взрыва при попадании снарядом в корабль
     */
    // TODO скопировано из примера. Нужно разобраться и отрефакторить
    public static void bulletHit(Ship ship, Projectile projectile) {
        projectile.facing.nor();
        float offset = 0;
//        if(ship instanceof FactoryProduction) offset = 50;
        if (ship instanceof Frigate) offset = 20;
//        if(ship instanceof Bomber) offset = 10;
//        if(ship instanceof Fighter) offset = 10;
        Vector2 pos = new Vector2().set(projectile.collisionCenter.x + (offset * projectile.facing.x), projectile.collisionCenter.y + (offset * projectile.facing.y));

        // ugh. . .
        Vector2 bullet_vel = new Vector2().set(projectile.velocity);

        Vector2 bullet_dir;
        if (bullet_vel.dot(bullet_vel) == 0) {
            bullet_dir = new Vector2();
        } else {
            bullet_dir = bullet_vel.nor();
        }
        Vector2 vel = new Vector2(bullet_dir.x * 1.5f, bullet_dir.y * 1.5f);

/*        if (projectile instanceof Laser) {
            laser_hit(pos, vel);
        } else if (projectile instanceof Bomb) {
            explosionParticles.addMediumExplosion(projectile.position);
        } else */
        if (projectile instanceof Missile) {
            GameInstance.getInstance().explosionParticles.addMediumExplosion(projectile.position);
        }
    }
}
