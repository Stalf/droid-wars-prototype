package com.droidwars.game.ai;

import com.droidwars.game.objects.ships.Ship;

/**
 * Интерфейс AI кораблей.
 */
public interface ShipAI {

    /**
     * Инициализация AI.
     * @param ship экземпляр корабля.
     */
    public void init(Ship ship);

    /**
     * Основной рабочий метод. Вызывается на каждом игровом такте.
     */
    public void update();

    /**
     * @return Наименование AI
     */
    public String getName();

}
