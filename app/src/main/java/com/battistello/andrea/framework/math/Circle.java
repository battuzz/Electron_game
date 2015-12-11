package com.battistello.andrea.framework.math;

public class Circle implements Shape {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius) {
        this.center.set(x,y);
        this.radius = radius;
    }

    public Circle(Vector2 center, float radius) {
        this.center.set(center.x, center.y);
        this.radius = radius;
    }

    public Circle copy() {
        return new Circle(this.center, this.radius);
    }

    @Override
    public void moveBy(float deltaX, float deltaY) {
        this.center.add(deltaX, deltaY);
    }

    @Override
    public Vector2 lowerLeft() {
        return new Vector2(center.x - radius, center.y - radius);
    }

    public float height() {
        return 2*radius;
    }

    public float width() {
        return 2*radius;
    }
}
