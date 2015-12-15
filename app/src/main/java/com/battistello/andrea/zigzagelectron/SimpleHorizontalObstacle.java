package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.math.Rectangle;

import java.util.Random;

/**
 * Created by Andrea on 15/12/15.
 */
public class SimpleHorizontalObstacle extends Obstacle {
    float height;
    public SimpleHorizontalObstacle(float starting_height) {
        super();
        float x = random_x();
        float y = starting_height;
        float width = 200.0f;
        float h = 50.0f;
        position.set(x, y);
        bounds = new Rectangle(x-width/2, y-h/2, width, h);

        height = 150.0f;
    }

    private float random_x() {
        Random r = new Random();
        boolean b = r.nextBoolean();
        if (b) {
            return 100.0f;
        }
        else {
            return 220.0f;
        }
    }
}
