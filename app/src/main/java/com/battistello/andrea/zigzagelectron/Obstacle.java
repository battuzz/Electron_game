package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.DynamicGameObject;
import com.battistello.andrea.framework.gl.SpriteBatcher;
import com.battistello.andrea.framework.math.Shape;
import com.battistello.andrea.framework.math.Vector2;

/**
 * Created by Andrea on 07/12/15.
 */
public abstract class Obstacle {
    Vector2 obsVelocity;

    public Obstacle() {
        obsVelocity = new Vector2(0, World.SCROLL_SPEED_Y);
    }

    public void setVelocity(float vx, float vy) {
        obsVelocity.set(vx, vy);
    }

    public abstract float getHeight();
    public abstract void update(float deltaTime);
    public abstract boolean collidesWith(Shape s);
    public abstract Vector2 getPosition();
    public abstract void render(SpriteBatcher b);
    public abstract void moveAt(Vector2 newPosition);

}
