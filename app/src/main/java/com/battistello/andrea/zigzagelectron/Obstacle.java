package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.DynamicGameObject;
import com.battistello.andrea.framework.math.Vector2;

/**
 * Created by Andrea on 07/12/15.
 */
public class Obstacle extends DynamicGameObject {
    public Obstacle() {
        super (0, 0, 0, 0);
        velocity = new Vector2(0, World.SCROLL_SPEED_Y);
    }
    public Obstacle(float x, float y, float width, float height) {
        super (x, y, width, height);
        this.velocity = new Vector2(0, World.SCROLL_SPEED_Y);
    }

    public void update(float deltaTime) {
        this.moveBy(velocity.x*deltaTime, velocity.y * deltaTime);
    }
}
