package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.DynamicGameObject;
import com.battistello.andrea.framework.gl.SpriteBatcher;
import com.battistello.andrea.framework.math.OverlapTester;
import com.battistello.andrea.framework.math.Rectangle;
import com.battistello.andrea.framework.math.Shape;
import com.battistello.andrea.framework.math.Vector2;

import java.util.Random;

/**
 * Created by Andrea on 15/12/15.
 */
public class SimpleHorizontalObstacle extends Obstacle {
    float height;
    DynamicGameObject obstacle;


    Random r;
    public SimpleHorizontalObstacle(float starting_height) {
        r = new Random();
        float x = random_x();
        float width = 200.0f;
        float h = 50.0f;


        obstacle = new DynamicGameObject(x, starting_height, width, h);
        obstacle.velocity.set(obsVelocity.x, obsVelocity.y);

        height = 150.0f;
    }

    private float random_x() {
        boolean b = r.nextBoolean();
        if (b) {
            return 100.0f;
        }
        else {
            return 220.0f;
        }
    }

    @Override
    public Vector2 getPosition() {
        return obstacle.position;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void update(float deltaTime) {
        obstacle.moveBy(obsVelocity.x * deltaTime, obsVelocity.y * deltaTime);
    }

    @Override
    public boolean collidesWith(Shape s) {
        return OverlapTester.overlap(s, obstacle.bounds);
    }

    @Override
    public void render(SpriteBatcher b) {
        b.drawSprite(obstacle.position.x, obstacle.position.y, obstacle.bounds.width(), obstacle.bounds.height(), Assets.obstacleRegion);
    }
}
