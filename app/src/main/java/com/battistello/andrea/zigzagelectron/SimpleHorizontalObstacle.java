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
//    DynamicGameObject obstacle;

    DynamicGameObject obstacle1, obstacle2;


    Random r;
    public SimpleHorizontalObstacle(float starting_height) {
        r = new Random();
        generateRandomObstacles(starting_height);


//        obstacle = new DynamicGameObject(x, starting_height, width, h);
//        obstacle.velocity.set(obsVelocity.x, obsVelocity.y);

        height = 250.0f;
    }

    private void generateRandomObstacles(float starting_height) {
        float x_left = r.nextFloat() * (World.WORLD_WIDTH - aperture_size);    //The x coordinate of the left side of the aperture
        float x_right = x_left + aperture_size;

        obstacle1 = new DynamicGameObject(x_left - World.WORLD_WIDTH/2, starting_height, World.WORLD_WIDTH, OBS_HEIGHT);
        obstacle2 = new DynamicGameObject(x_right + World.WORLD_WIDTH/2, starting_height, World.WORLD_WIDTH, OBS_HEIGHT);
    }

    @Override
    public Vector2 getPosition() {
//        return obstacle.position;
        return obstacle1.position;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void update(float deltaTime) {
//        obstacle.moveBy(obsVelocity.x * deltaTime, obsVelocity.y * deltaTime);
        obstacle1.moveBy(obsVelocity.x * deltaTime, obsVelocity.y * deltaTime);
        obstacle2.moveBy(obsVelocity.x * deltaTime, obsVelocity.y * deltaTime);
    }

    @Override
    public boolean collidesWith(Shape s) {
//        return OverlapTester.overlap(s, obstacle.bounds);
        return OverlapTester.overlap(s, obstacle1.bounds) || OverlapTester.overlap(s, obstacle2.bounds);
    }

    @Override
    public void render(SpriteBatcher b) {
//        b.drawSprite(obstacle.position.x, obstacle.position.y, obstacle.bounds.width(), obstacle.bounds.height(), Assets.obstacleRegion);
        b.drawSprite(obstacle1.position.x, obstacle1.position.y, obstacle1.bounds.width(), obstacle1.bounds.height(), Assets.obstacleRegion);
        b.drawSprite(obstacle2.position.x, obstacle2.position.y, obstacle2.bounds.width(), obstacle2.bounds.height(), Assets.obstacleRegion);
    }
}
