package com.example.collinsherman.asteroidzgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.collinsherman.asteroidzgame.model.components.Speed;

import java.util.concurrent.ThreadLocalRandom;

public class Star {

    private Bitmap bitmap;
    private int x;
    private int y;
    private Speed speed;

    public Star(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        speed = new Speed(0, 37);
    }

    public int getLeft() {
        return (x - bitmap.getWidth() / 2);
    }

    public int getRight() {
        return (x + bitmap.getWidth() / 2);
    }

    public int getTop() {
        return (y - bitmap.getHeight() / 2);
    }

    public int getBottom() {
        return (y + bitmap.getHeight() / 2);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }

    public void update() {
        x += (speed.getXVel());
        y += (speed.getYVel() * speed.getYDir());
    }
}
