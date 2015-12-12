package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.DynamicGameObject;
import com.battistello.andrea.framework.math.Vector2;

/**
 * Created by Andrea on 05/12/15.
 */
public class Particle extends DynamicGameObject {
    float radius;
    int charge;
    float mass;
    float interactionRing = 80.0f;       // The radius from where the interaction with other particles become relevant

    public Particle(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.mass = 1.0f;
        this.charge = 1;
        this.radius = 20.0f;
    }

    public void update(float deltaTime) {
        velocity.add(accel.x * deltaTime, accel.y * deltaTime);
        moveBy(velocity.x * deltaTime, velocity.y * deltaTime);
    }

}
