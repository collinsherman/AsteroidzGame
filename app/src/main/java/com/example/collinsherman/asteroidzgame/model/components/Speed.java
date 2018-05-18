package com.example.collinsherman.asteroidzgame.model.components;

import java.util.concurrent.ThreadLocalRandom;

public class Speed {

    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_RIGHT = 1;

    int randomX = ThreadLocalRandom.current().nextInt(0, 4);
    int randomY = ThreadLocalRandom.current().nextInt(8, 21);

    private float xVel = randomX;
    private float yVel = randomY;
    private int yDir = DIRECTION_DOWN;
    private int xDir = DIRECTION_RIGHT;

    public Speed() {
        this.xVel = randomX;
        this.yVel = randomY;
        this.toggleXDirection();
    }

    public Speed(float xVel, float yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
        this.toggleXDirection();
    }

    // Velocity methods
    public float getXVel() { return xVel; }

    public void setXVel(float xVel) { this.xVel = xVel; }

    public float getYVel() { return yVel; }

    public void setYVel(float yVel) {this.yVel = yVel; }

    // Direction methods
    public int getYDir() { return yDir; }

    public int getXDir() { return xDir; }

    public void setYDir(int yDir) { this.yDir = yDir; }

    public void setXDir(int xDir) { this.xDir = xDir; }

    public void toggleYDirection() {
        int randomChange = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomChange == 1) {
            yDir = yDir * -1;
        }
    }

    public void toggleXDirection() {
        int randomChange = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomChange == 1) {
            xVel = xVel * -1;
        }
    }
}
