package com.battistello.andrea.framework.math;

/**
 * Created by Andrea on 05/12/15.
 */
public interface Shape {
    public Shape copy();
    public void moveBy(float deltaX, float deltaY);

    public Vector2 lowerLeft();
    public float height();
    public float width();
}
