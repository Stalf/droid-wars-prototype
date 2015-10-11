package com.droidwars.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.droidwars.game.objects.projectiles.Missile;
import com.droidwars.game.objects.projectiles.Projectile;
import com.droidwars.game.objects.ships.Frigate;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.particles.BigBubbleParticleEmitter;
import com.droidwars.game.particles.BubbleParticleEmitter;
import com.droidwars.game.particles.ExplosionParticleEmitter;
import com.droidwars.game.particles.SparkParticleEmitter;
import com.droidwars.game.utils.GameUtils;

public class GameInstance {

    public Array<Ship> ships = new Array<Ship>();
    public Array<Projectile> projectiles = new Array<Projectile>();

    public BubbleParticleEmitter bubbleParticles = new BubbleParticleEmitter();
    public BigBubbleParticleEmitter bigBubbleParticles = new BigBubbleParticleEmitter();
    public SparkParticleEmitter sparkParticles = new SparkParticleEmitter();
    public ExplosionParticleEmitter explosionParticles = new ExplosionParticleEmitter();

    public boolean gameOver = false;

    private static GameInstance instance;

    public static GameInstance getInstance() {
        if (instance == null) {
            instance = new GameInstance();
        }
        return instance;
    }

    public void resetGame() {
        GameInstance gameInstance = getInstance();

        gameInstance.gameOver = false;

        gameInstance.ships.clear();
        gameInstance.projectiles.clear();

        bubbleParticles.dispose();
        bigBubbleParticles.dispose();
        sparkParticles.dispose();
        explosionParticles.dispose();

        bubbleParticles = new BubbleParticleEmitter();
        bigBubbleParticles = new BigBubbleParticleEmitter();

        sparkParticles = new SparkParticleEmitter();
        explosionParticles = new ExplosionParticleEmitter();

    }

    public void startNewGame() {
        GameInstance gameInstance = getInstance();
        gameInstance.gameOver = false;
        gameInstance.ships.add(new Frigate(0, GameUtils.generateRandomPosition(), GameUtils.generateRandomDirection()));
        gameInstance.ships.add(new Frigate(1, GameUtils.generateRandomPosition(), GameUtils.generateRandomDirection()));
    }

    public void renderAll(Batch gameBatch) {

        GameUtils.collisionCheck();

        GameInstance.getInstance().bubbleParticles.draw(gameBatch);
        GameInstance.getInstance().bigBubbleParticles.draw(gameBatch);
        GameInstance.getInstance().sparkParticles.draw(gameBatch);
        GameInstance.getInstance().explosionParticles.draw(gameBatch);

        // Рендерим корабли
        for (Ship ship : GameInstance.getInstance().ships) {
            if (ship.alive) {
                ship.draw(gameBatch);
            } else {
                gameOver = true;
//                GameInstance.getInstance().ships.removeValue(ship, true);
            }
        }

        // Рендерим снаряды
        for (Projectile projectile : GameInstance.getInstance().projectiles) {
            if (projectile.alive) {
                projectile.draw(gameBatch);
            } else {
                GameInstance.getInstance().projectiles.removeValue(projectile, true);
            }
        }
    }

}
