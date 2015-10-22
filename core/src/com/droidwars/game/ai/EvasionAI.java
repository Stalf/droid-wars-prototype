package com.droidwars.game.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.projectiles.Projectile;
import com.droidwars.game.utils.GameUtils;

import java.util.Iterator;

/**
 * Уворачивается от ракет с помощью strafe
 */
// TODO не закончено, заготовка
public class EvasionAI extends AbstractShipAI {

    @Override
    public void update() {

        Projectile nearest = null;
        float minDist = Integer.MAX_VALUE;
        for (Projectile next : GameInstance.getInstance().projectiles) {

            float dst = next.position.dst(ship.position);

            if (dst < minDist) {
                minDist = dst;
                nearest = next;
            }
        }

        if (nearest != null) {
            boolean danger = checkIntersect(ship.position, ship.velocity, nearest.position, nearest.velocity);

            if (danger) {
                ship.strafe(1);
            }
        }

    }

    public boolean checkIntersect(Vector2 pos1, Vector2 vel1, Vector2 pos2, Vector2 vel2) {

        float vxratio = vel1.x / vel2.x;
        float x = (pos1.x - vxratio*pos2.x)/(1 - vxratio);

        float vyratio = vel1.y / vel2.y;
        float y = (pos1.y - vyratio*pos2.y)/(1 - vyratio);

        Gdx.app.debug("intersec", String.format("x = 2.%f; y = 2.%f", x, y));

//        float t1 = (pos1.x - pos2.x) * (vel2.y - vel1.y);
//        float t2 = (pos1.y - pos2.y) * (vel2.x - vel1.x) ;

        return true;//(GameUtils.equals(t1, t2, GameUtils.EPSILON));
    }



    @Override
    public String getName() {
        return "Evading rockets";
    }
}
