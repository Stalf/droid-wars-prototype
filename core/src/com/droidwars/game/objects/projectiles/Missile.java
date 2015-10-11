package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.Resources;
import com.droidwars.game.objects.ships.Ship;

public class Missile extends Projectile {

    public Missile(Ship ship) {
        super(ship);

        damage = 100;
        maxTravelDistance = 700;
        maxVelocity = 150;

        this.velocity.set(this.facing).scl(maxVelocity).add(ship.velocity);

        setSprite(Resources.getInstance().missiles.get(id));
    }
}
