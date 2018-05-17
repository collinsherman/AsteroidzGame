package com.example.collinsherman.asteroidzgame.model.components;

public class Speed {

    public static final int DIRECTION_DOWN = 1;

    private float xVel = 1;
    private float yVel = 1;
    private int yDir = DIRECTION_DOWN;

    public Speed() {
        this.xVel = 1;
        this.yVel = 1;
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
}
