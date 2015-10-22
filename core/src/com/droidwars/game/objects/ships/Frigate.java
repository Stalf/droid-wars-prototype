package com.droidwars.game.objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.Resources;
import com.droidwars.game.ai.EvasionAI;
import com.droidwars.game.ai.RandomShootAI;
import com.droidwars.game.ai.ShipAI;
import com.droidwars.game.objects.projectiles.Missile;

public class Frigate extends Ship {

    public Frigate(int id, Vector2 position, Vector2 facing) {
        super(id, position, facing);

        turnSpeed = 90f;
        strafe = 5.0f;
        accel = 20.0f;
        maxHitPoints = 2000;
        hitPoints = maxHitPoints;

        ai = new EvasionAI();
        ai.init(this);

        this.setSprite(Resources.getInstance().frigates.get(id));
    }

    public Frigate(int id, Vector2 position, Vector2 facing, ShipAI shipAI) {
        super(id, position, facing);

        turnSpeed = 90f;
        strafe = 5.0f;
        accel = 20.0f;
        maxHitPoints = 2000;
        hitPoints = maxHitPoints;

        ai = shipAI;
        ai.init(this);

        this.setSprite(Resources.getInstance().frigates.get(id));
    }

    @Override
    public void shoot() {
        GameInstance.getInstance().projectiles.add(new Missile(this));
    }

}
