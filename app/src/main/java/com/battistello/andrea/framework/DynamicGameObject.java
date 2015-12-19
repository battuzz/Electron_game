package com.battistello.andrea.framework;

import com.battistello.andrea.framework.math.Vector2;

public class DynamicGameObject extends GameObject {
    public Vector2 velocity;
    public final Vector2 accel;
    
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }

    public DynamicGameObject(Vector2 position, float width, float height, Vector2 velocity, Vector2 accel) {
        super(position.x, position.y, width, height);
        this.velocity = new Vector2(velocity);
        this.accel = new Vector2(accel);
    }

    public void moveBy(float deltaX, float deltaY) {
        position.add(deltaX, deltaY);
        bounds.moveBy(deltaX, deltaY);
    }

    public void update(float deltaTime) {
        moveBy(velocity.x * deltaTime, velocity.y * deltaTime);
    }
}
