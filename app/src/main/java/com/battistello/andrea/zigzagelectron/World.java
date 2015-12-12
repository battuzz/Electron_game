package com.battistello.andrea.zigzagelectron;


import android.util.Log;

import com.battistello.andrea.framework.math.OverlapTester;
import com.battistello.andrea.framework.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Andrea on 07/12/15.
 */
public class World {
    public static final float MAIN_OFFSET_Y = 20.0f;     // Distance of the main electron from the bottom edge
    public static final float MAIN_RADIUS = 20.0f;
    public static final float WORLD_WIDTH = 320;
    public static final float WORLD_HEIGHT = 480;
    public static final float INITIAL_E_X = 20.0f;        // initial intensity of electric field on x
    public static final float INITIAL_E_Y = 0.0f;          // initial intensity of electric field on y
    public static final float MAX_SCROLL_SPEED_Y = -500.0f;
    public static float SCROLL_SPEED_Y;


    private float current_height;               // The distance of the main electron to the world 'ground'
    private float generated_world_max_height;       // The max height of the generated world

    public enum State {
        GAME_INITIALIZING,
        GAME_RUNNING,
        GAME_END
    }
    State state;


    Vector2 E;                               // intensity of electric field
    private Particle mainElectron;
    private ArrayList<Item> items;
    ArrayList<Obstacle> obstacles;
    private WorldListener listener;



    public interface WorldListener {
        void gamePaused();
        void gameEnded();

    }


    public World(WorldListener listener) {
        this.listener = listener;
        state = State.GAME_INITIALIZING;




        generateWorld();

        state = State.GAME_RUNNING;

    }

    public void restart() {
        state = State.GAME_INITIALIZING;
        generateWorld();

        state = State.GAME_RUNNING;
    }


    public void update(float deltaTime) {
        // calculate the forces to apply to each element in the world
        calculateForces();

        // update the position of each element in the world
        updateMainElectron(deltaTime);
        updateObstacles(deltaTime);

        // check for collisions
        checkCollisions();


        // Update the world

//        current_height = current_height + Math.abs(SCROLL_SPEED_Y)*deltaTime;


        // Check if we need to add more obstacles in the world
        generated_world_max_height += SCROLL_SPEED_Y * deltaTime;
        if (generated_world_max_height <= WORLD_HEIGHT + 100) {
            generateObstacles();
        }

        // Increase the scrolling speed to a maximum of MAX_SCOLL_SPEED_Y
        SCROLL_SPEED_Y = Math.max(SCROLL_SPEED_Y - 0.1f * deltaTime, MAX_SCROLL_SPEED_Y);

        Log.d("MYDEBUG", "current_height: " + current_height + " generated_world_max_height: " + generated_world_max_height);

    }

    private void generateWorld() {
        SCROLL_SPEED_Y = -20.0f;                    // Scrolling speed is downward
        generated_world_max_height = 200;
        current_height = 0;
        items = new ArrayList<Item>();
        obstacles = new ArrayList<Obstacle>();
        mainElectron = new Particle(WORLD_WIDTH/2, MAIN_OFFSET_Y, MAIN_RADIUS, MAIN_RADIUS);

        E = new Vector2(INITIAL_E_X, INITIAL_E_Y);

        generateObstacles();
    }

    private void generateObstacles() {
        for (int i = 0; i < 5; i++) {
            Obstacle obs = new Obstacle(0, generated_world_max_height + 100, 200, 50);
            obs.velocity.y = SCROLL_SPEED_Y;
            obstacles.add(obs);
            generated_world_max_height += 100;
        }
    }

    private void calculateForces() {
        // At the moment the only item subject to forces is the main Electron

        float posX = mainElectron.position.x;
        float posY = mainElectron.position.y;

        float adjFactor = 1.0f;
        if (posX <= mainElectron.interactionRing) {
            adjFactor = 1.0f + 20.0f * ((mainElectron.interactionRing - posX)*(mainElectron.interactionRing - posX)) / (mainElectron.interactionRing * mainElectron.interactionRing);
        }
        else if (posX >= WORLD_WIDTH - mainElectron.interactionRing) {
            adjFactor = 1.0f + 20.0f *  ((mainElectron.interactionRing - WORLD_WIDTH + posX)*(mainElectron.interactionRing - WORLD_WIDTH + posX) / (mainElectron.interactionRing * mainElectron.interactionRing));
        }

        mainElectron.accel.set(E.x * adjFactor * mainElectron.charge / mainElectron.mass, 0);
    }

    private void updateMainElectron(float deltaTime) {
        mainElectron.update(deltaTime);
    }

    private void updateObstacles(float deltaTime) {
        Iterator<Obstacle> it = obstacles.iterator();
        while (it.hasNext()) {
            Obstacle ob = it.next();
            if (ob.position.y < current_height - 200) {
                it.remove();
            }
            else {
                ob.update(deltaTime);
                ob.velocity.y = SCROLL_SPEED_Y;
            }
        }
    }

    private void checkCollisions() {
        if (mainElectron.position.x - mainElectron.radius <= 0 || mainElectron.position.x + mainElectron.radius >= WORLD_WIDTH) {
            // collision with the shields of the capacitor
            state = State.GAME_END;
            listener.gameEnded();
        }

        // Check collisions with obstacles
        for (Obstacle obs :
                obstacles) {
            if (OverlapTester.overlap(obs.bounds, mainElectron.bounds)) {
                state = State.GAME_END;
                listener.gameEnded();
            }
        }
    }

    public void invertPolarity() {
        E.mul(-1);
    }

    public Particle getMainElectron() {
        return mainElectron;
    }
}
