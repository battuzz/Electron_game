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
    public final float OBS_HEIGHT = 50.0f;
    public final float aperture_size = World.WORLD_WIDTH / 4;    // Width of the space between the two obstacles where the main electron should pass through.
    float height;

    DynamicGameObject obstacle1, obstacle2;


    Random r;
    public SimpleHorizontalObstacle() {
        r = new Random();
        generateRandomObstacles();

        height = 250.0f;
    }

    private void generateRandomObstacles() {
        float starting_height = 0;
        float x_left = r.nextFloat() * (World.WORLD_WIDTH - aperture_size);    //The x coordinate of the left side of the aperture
        float x_right = x_left + aperture_size;

        obstacle1 = new DynamicGameObject(x_left - World.WORLD_WIDTH/2, starting_height, World.WORLD_WIDTH, OBS_HEIGHT);
        obstacle2 = new DynamicGameObject(x_right + World.WORLD_WIDTH/2, starting_height, World.WORLD_WIDTH, OBS_HEIGHT);
    }

    @Override
    public Vector2 getPosition() {
        return obstacle1.position;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void update(float deltaTime) {
        obstacle1.moveBy(obsVelocity.x * deltaTime, obsVelocity.y * deltaTime);
        obstacle2.moveBy(obsVelocity.x * deltaTime, obsVelocity.y * deltaTime);
    }

    @Override
    public boolean collidesWith(Shape s) {
        return OverlapTester.overlap(s, obstacle1.bounds) || OverlapTester.overlap(s, obstacle2.bounds);
    }

    @Override
    public void render(SpriteBatcher b) {
        b.drawSprite(obstacle1.position.x, obstacle1.position.y, obstacle1.bounds.width(), obstacle1.bounds.height(), Assets.obstacleRegion);
        b.drawSprite(obstacle2.position.x, obstacle2.position.y, obstacle2.bounds.width(), obstacle2.bounds.height(), Assets.obstacleRegion);
    }

    @Override
    public void moveAt(Vector2 newPosition) {
        Vector2 diff1 = newPosition.cpy().sub(obstacle1.position);
        Vector2 diff2 = newPosition.cpy().sub(obstacle2.position);

        obstacle1.moveBy(diff1.x, diff1.y);
        obstacle2.moveBy(diff2.x, diff2.y);
    }
}
