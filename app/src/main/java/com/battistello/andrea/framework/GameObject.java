package com.battistello.andrea.framework;

import com.battistello.andrea.framework.math.Circle;
import com.battistello.andrea.framework.math.Rectangle;
import com.battistello.andrea.framework.math.Shape;
import com.battistello.andrea.framework.math.Vector2;

public class GameObject {
    public final Vector2 position;
//    public final Rectangle bounds;
    public final Shape bounds;

    /**
     * Creates a game object that represents a generic physical object in the game.
     * Note: the object is described by the coordinate of its centre.
     *
     * @param x the x-coordinate of the centre
     * @param y the y-coordinate of the centre
     * @param width the width of the entire object
     * @param height the height of the entire object
     */
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x,y);
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
    }

    public GameObject(float x, float y, float radius) {
        this.position = new Vector2(x, y);
        this.bounds = new Circle(x, y, radius);
    }


    /**
     * Creates a generic physical object on the game
     *
     * @param x the x-coordinate of the centre
     * @param y the y-coordinate of the centre
     * @param bounds a Rectangle that represent its bounds
     */
    public GameObject(float x, float y, Shape bounds) {
        this.position = new Vector2(x, y);
        this.bounds = bounds.copy();
    }

}
