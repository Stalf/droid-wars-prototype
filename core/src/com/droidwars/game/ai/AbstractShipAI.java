package com.droidwars.game.ai;

import com.droidwars.game.objects.ships.Ship;

public abstract class AbstractShipAI implements ShipAI{

    protected Ship ship;

    @Override
    public void init(Ship ship) {
        this.ship = ship;
    }
}
