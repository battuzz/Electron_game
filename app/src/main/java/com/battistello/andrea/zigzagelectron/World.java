package com.battistello.andrea.zigzagelectron;


import android.util.Log;

import com.battistello.andrea.framework.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Andrea on 07/12/15.
 */
public class World {
    public static final float MAIN_OFFSET_Y = 20.0f;
    public static final float MAIN_RADIUS = 20.0f;
    public static final float WORLD_WIDTH = 320;
    public static final float WORLD_HEIGHT = 480;
    public static final float INITIAL_E_X = 20.0f;        // initial intensity of electric field on x
    public static final float INITIAL_E_Y = 0.0f;          // initial intensity of electric field on y
    public static final float SCROLL_SPEED_Y = 20.0f;


    public enum State {
        GAME_INITIALIZING,
        GAME_RUNNING,
        GAME_END
    };
    State state;


    Vector2 E;                               // intensity of electric field
    private Particle mainElectron;
    private ArrayList<Item> items;
    private ArrayList<Obstacle> obstacles;
    private WorldListener listener;



    public interface WorldListener {
        public void gamePaused();
        public void gameEnded();

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
        updateObstacles();

        // check for collisions
        checkCollisions();
    }

    private void generateWorld() {
        items = new ArrayList<Item>();
        obstacles = new ArrayList<Obstacle>();
        mainElectron = new Particle(WORLD_WIDTH/2, MAIN_OFFSET_Y, MAIN_RADIUS, MAIN_RADIUS);

        E = new Vector2(INITIAL_E_X, INITIAL_E_Y);
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

    private void updateObstacles() {

    }

    private void checkCollisions() {
        if (mainElectron.position.x - mainElectron.radius <= 0 || mainElectron.position.x + mainElectron.radius >= WORLD_WIDTH) {
            // collision with the shields of the capacitor
            state = State.GAME_END;
            listener.gameEnded();
        }
    }

    public void invertPolarity() {
        E.mul(-1);
    }

    public Particle getMainElectron() {
        return mainElectron;
    }
}
