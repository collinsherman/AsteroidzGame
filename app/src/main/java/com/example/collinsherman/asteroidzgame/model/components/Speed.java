package com.example.collinsherman.asteroidzgame.model.components;

public class Speed {

    public static final int DIRECTION_DOWN = 1;

    private float xVel = 0;
    private float yVel = 10;
    private int yDir = DIRECTION_DOWN;

    public Speed() {
        this.xVel = 0;
        this.yVel = 10;
    }

    public Speed(float xVel, float yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }

    // Velocity methods
    public float getXVel() { return xVel; }

    public void setXVel(float xVel) { this.xVel = xVel; }

    public float getYVel() { return yVel; }

    public void setYVel(float yVel) {this.yVel = yVel; }

    // Direction methods
    public int getYDir() { return yDir; }

    public void setYDir(int yDir) { this.yDir = yDir; }

    public void toggleYDirection() {
        yDir = yDir * -1;
    }
}
