package com.battistello.andrea.zigzagelectron;

/**
 * Created by Andrea on 23/12/15.
 */
public class ObstacleGenerator {
    World world;
    public ObstacleGenerator(World world) {
        this.world = world;
    }

    public Obstacle generate() {
        return new SimpleHorizontalObstacle();
    }
}
