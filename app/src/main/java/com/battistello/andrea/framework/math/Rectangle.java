package com.battistello.andrea.framework.math;

public class Rectangle implements Shape {
    public final Vector2 lowerLeft;
    public float width, height;
    
    public Rectangle(float x, float y, float width, float height) {
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }

    public Rectangle copy() {
        return new Rectangle(this.lowerLeft.x, this.lowerLeft.y, this.width, this.height);
    }

    public void moveBy(float deltaX, float deltaY) {
        this.lowerLeft.add(deltaX, deltaY);
    }

    public Vector2 lowerLeft() {
        return this.lowerLeft;
    }
    public float height() {
        return this.height;
    }
    public float width() {
        return this.width;
    }
}
