package com.droidwars.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.droidwars.game.utils.Constants;

/**
 * Абстрактный объект игрового мира
 */
public abstract class GameObject extends Sprite {
    public float aliveTime = 0.0f;
    public float travelDistance = 0.0f;

    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public Vector2 facing = new Vector2();

    public Vector2 collisionCenter = new Vector2();
    public Array<Vector2> collisionPoints = new Array<Vector2>();

    public boolean alive = true;

    // В данный момент id используется для определения принадлежности объекта игроку
    public int id = 0;

    protected float delta = 0.0f;

    public GameObject(int id, Vector2 position, Vector2 facing) {
        super();

        this.id = id;

        this.position.set(position);
        this.facing.set(facing.nor());

        collisionPoints.clear();
        collisionPoints.add(new Vector2());
        collisionPoints.add(new Vector2());
        collisionPoints.add(new Vector2());
        collisionPoints.add(new Vector2());

        this.setOrigin(this.getWidth() / 2.f, this.getHeight() / 2.f);
    }

    public void setSprite(Sprite sprite) {
        this.set(sprite);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
    }

    /**
     * Реализует перемещение корабля по поверхности тора
     */
    // TODO необходимо реализовать на уровне мирового движка
    protected void applyTorusMap() {
        if (position.x > Constants.MAP_WIDTH) {
            position.x = 0;
        }
        if (position.x < 0) {
            position.x = Constants.MAP_WIDTH;
        }
        if (position.y > Constants.MAP_HEIGHT) {
            position.y = 0;
        }
        if (position.y < 0) {
            position.y = Constants.MAP_HEIGHT;
        }
    }

    @Override
    public void draw(Batch batch) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

        aliveTime += delta;
        collisionPoints.get(0).set(this.getVertices()[0], this.getVertices()[1]);
        collisionPoints.get(1).set(this.getVertices()[5], this.getVertices()[6]);
        collisionPoints.get(2).set(this.getVertices()[10], this.getVertices()[11]);
        collisionPoints.get(3).set(this.getVertices()[15], this.getVertices()[16]);

        collisionCenter.set(collisionPoints.get(0)).add(collisionPoints.get(2)).scl(0.5f);

//		velocity.scl( (float) Math.pow(0.97f, delta * 30.f)); - код был в примере. для чего он - не понимаю
        position.add(velocity.x * delta, velocity.y * delta);
        travelDistance += velocity.len() * delta;

        applyTorusMap();

        this.setRotation(facing.angle());
        this.setPosition(position.x, position.y);

        super.draw(batch);
    }

    /**
     * Метод уничтожает объект
     */
    public void destroy() {
        this.alive = false;
    }
}
